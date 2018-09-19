package com.octobus.article.exception;

public class ProfilePhotoNotFoundException extends RuntimeException {
    public ProfilePhotoNotFoundException(String message) {
        super(message);
    }

    public ProfilePhotoNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
