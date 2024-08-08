package com.URLShortner.app.controller;

import com.URLShortner.app.model.ShortenUriResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.URLShortner.app.service.UrlService;

@RestController
@RequestMapping("/api/url")
public class UrlController {

    @Autowired
    private UrlService urlService;

    @PostMapping("/shorten")
    public ResponseEntity<ShortenUriResponse> shortenUrl(@RequestParam String destinationUrl) {
        return ResponseEntity.ok(urlService.shortenUrl(destinationUrl));
    }

    @PostMapping("/update")
    public ResponseEntity<Boolean> updateUrl(@RequestParam String shortUrl, @RequestParam String destinationUrl) {
        return ResponseEntity.ok(urlService.updateUrl(shortUrl, destinationUrl));
    }

    @GetMapping("/destination")
    public ResponseEntity<String> getDestinationUrl(@RequestParam String shortUrl) {
        return ResponseEntity.ok(urlService.getDestinationUrl(shortUrl));
    }

    @PostMapping("/update_expiry")
    public ResponseEntity<Boolean> updateExpiry(@RequestParam String shortUrl, @RequestParam int daysToAdd) {
        return ResponseEntity.ok(urlService.updateExpiry(shortUrl, daysToAdd));
    }
}
