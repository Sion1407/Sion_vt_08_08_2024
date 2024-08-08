package com.URLShortner.app.service;

import com.URLShortner.app.entity.Url;
import com.URLShortner.app.exceptions.UrlNotFoundException;
import com.URLShortner.app.model.ShortenUriResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import com.URLShortner.app.repository.UrlRepository;
import com.URLShortner.app.utils.Constants;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@Service
public class UrlService {
    private final Random random = new Random();

    @Autowired
    private UrlRepository urlRepository;



    public ShortenUriResponse shortenUrl(String destinationUrl) {
        ShortenUriResponse shortenUriResponse = new ShortenUriResponse();
        try {
            String shortUrl = generateShortUrl();
            Url url = new Url(shortUrl, destinationUrl, LocalDateTime.now(), LocalDateTime.now().plusMonths(10));
            urlRepository.save(url);

            // Set success response
            shortenUriResponse.setId(Constants.SUCCESS);
            shortenUriResponse.setShortUrl(shortUrl);
        } catch (Exception e) {
            // Log the exception for debugging purposes
            e.printStackTrace();

            // Set failure response
            shortenUriResponse.setId(Constants.FAILURE);
            shortenUriResponse.setShortUrl(null);  // Optionally set this to a meaningful message if needed
        }
        return shortenUriResponse;
    }

    public boolean updateUrl(String shortUrl, String destinationUrl) {
        Url url = urlRepository.findByShortUrl(shortUrl);
        if (url != null) {
            url.setDestinationUrl(destinationUrl);
            urlRepository.save(url);
            return true;
        }
        return false;
    }

    public String getDestinationUrl(String shortUrl) {
        String destinationUrl;
        Url url = urlRepository.findByShortUrl(shortUrl);
        if (url != null && url.getExpiresAt().isAfter(LocalDateTime.now())) {
            destinationUrl = url.getDestinationUrl();
        } else {
            throw new UrlNotFoundException();
        }

        return destinationUrl;
    }

    public boolean updateExpiry(String shortUrl, int daysToAdd) {
        Url url = urlRepository.findByShortUrl(shortUrl);
        if (url != null) {
            url.setExpiresAt(url.getExpiresAt().plusDays(daysToAdd));
            urlRepository.save(url);
//            long secondsUntilExpiry = url.getExpiresAt().toEpochSecond(ZoneOffset.UTC) - LocalDateTime.now().toEpochSecond(ZoneOffset.UTC);
            return true;
        }
        return false;
    }

    private String generateShortUrl() {
        StringBuilder shortUrl = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            shortUrl.append(Constants.BASE62.charAt(random.nextInt(Constants.BASE62.length())));
        }
        return Constants.INITIAL_URL+shortUrl;
    }

}
