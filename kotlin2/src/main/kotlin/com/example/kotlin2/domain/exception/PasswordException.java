package com.example.kotlin2.domain.exception;

public class PasswordException extends RuntimeException{
    public PasswordException(String message) {
        super(message);
    }
}
