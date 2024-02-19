package com.example.orderapp.domain.exception;

public class CanNotCancellableStatusException extends RuntimeException {
    public CanNotCancellableStatusException(String message) {
        super(message);
    }
}
