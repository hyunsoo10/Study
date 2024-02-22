package com.example.kotlin2.domain.exception;

public class CanNotCancellableStatusException extends RuntimeException {
    public CanNotCancellableStatusException(String message) {
        super(message);
    }
}
