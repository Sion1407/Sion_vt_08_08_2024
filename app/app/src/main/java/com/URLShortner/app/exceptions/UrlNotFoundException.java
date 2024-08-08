package com.URLShortner.app.exceptions;

public class UrlNotFoundException extends RuntimeException {
    public UrlNotFoundException() {
        super("URL not found or has expired");
    }
}

