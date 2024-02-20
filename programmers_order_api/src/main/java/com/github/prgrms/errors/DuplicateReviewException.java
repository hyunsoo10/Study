package com.github.prgrms.errors;

public class DuplicateReviewException extends RuntimeException{

    public DuplicateReviewException(String message) {
        super(message);
    }
}
