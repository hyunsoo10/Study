package com.example.kotlin2.presentation.controller;

import com.example.kotlin2.domain.exception.CanNotCancellableStatusException;
import com.example.kotlin2.domain.exception.EntityNotFoundException;
import com.example.kotlin2.domain.exception.NotEnoughStockException;
import com.example.kotlin2.domain.exception.UserNotFoundException;
import com.example.kotlin2.presentation.dto.ErrorMessageDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionController {

    @ExceptionHandler(NotEnoughStockException.class)
    public ResponseEntity<ErrorMessageDto> handleNotEnoughStockException(
            NotEnoughStockException ex
    ) {
        ErrorMessageDto errorMessageDto = new ErrorMessageDto(ex.getMessage());
        return new ResponseEntity<>(errorMessageDto, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorMessageDto> handleEntityNotFoundException(
            EntityNotFoundException ex
    ) {
        ErrorMessageDto errorMessageDto = new ErrorMessageDto(ex.getMessage());
        return new ResponseEntity<>(errorMessageDto, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CanNotCancellableStatusException.class)
    public ResponseEntity<ErrorMessageDto> handleCanNotCancelException(CanNotCancellableStatusException ex) {
        ErrorMessageDto errorMessageDto = new ErrorMessageDto(ex.getMessage());
        return new ResponseEntity<>(errorMessageDto, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorMessageDto> handleUserNotFoundException(UserNotFoundException ex) {
        ErrorMessageDto errorMessageDto = new ErrorMessageDto(ex.getMessage());
        return new ResponseEntity<>(errorMessageDto, HttpStatus.NOT_FOUND);
    }
}
