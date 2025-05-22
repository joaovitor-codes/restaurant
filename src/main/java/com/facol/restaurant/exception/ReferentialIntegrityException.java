package com.facol.restaurant.exception;

public class ReferentialIntegrityException extends RuntimeException {
    public ReferentialIntegrityException(String message) {
        super(message);
    }
}
