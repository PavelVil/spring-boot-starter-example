package com.github.pavelvil.springbootstarterconcurrency.exception;

public class ConcurrencyStartupException extends RuntimeException {
    public ConcurrencyStartupException() {
    }

    public ConcurrencyStartupException(String message) {
        super(message);
    }
}
