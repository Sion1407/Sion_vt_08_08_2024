package com.URLShortner.app.controller;

import com.URLShortner.app.entity.Url;
import com.URLShortner.app.repository.UrlRepository;
import com.URLShortner.app.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Optional;

@RestController
public class RedirectionURLController {
    @Autowired
    UrlRepository urlRepository;

    @GetMapping("/{shortUrl}")
    public ResponseEntity<Void> redirectToOriginalUrl(@PathVariable String shortUrl) {
        // Construct the full short URL with the base URL
        String fullShortUrl = Constants.INITIAL_URL + shortUrl;

        // Query the database using the full short URL
        Url url = urlRepository.findByShortUrl(fullShortUrl);

        if (url != null) {
            try {
                // Redirect to the original URL
                return ResponseEntity.status(HttpStatus.FOUND)
                        .location(new URI(url.getDestinationUrl()))
                        .build();
            } catch (URISyntaxException e) {
                // Handle exception if URL is malformed
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            }
        } else {
            // If the short URL is not found, return a 404 response
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

}
