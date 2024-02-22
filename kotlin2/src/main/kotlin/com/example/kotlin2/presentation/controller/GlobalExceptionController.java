package com.example.kotlin2.presentation.controller;

import com.auth0.jwt.exceptions.JWTCreationException;
import com.example.kotlin2.domain.exception.*;
import com.example.kotlin2.presentation.dto.ErrorMessageDto;
import io.jsonwebtoken.JwtException;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;

import javax.naming.AuthenticationException;

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

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<ErrorMessageDto> handleUnAuthorizedException(AuthenticationException ex) {
        ErrorMessageDto errorMessageDto = new ErrorMessageDto("권한이 없습니다.");
        return new ResponseEntity<>(errorMessageDto, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(PasswordException.class)
    public ResponseEntity<ErrorMessageDto> handlePasswordException(PasswordException ex) {
        ErrorMessageDto errorMessageDto = new ErrorMessageDto(ex.getMessage());
        return new ResponseEntity<>(errorMessageDto, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ErrorMessageDto> handleBadCredentialsException(BadCredentialsException ex) {
        ErrorMessageDto errorMessageDto = new ErrorMessageDto("자격 증명에 실패하였습니다.");
        return new ResponseEntity<>(errorMessageDto, HttpStatus.UNAUTHORIZED);
    }
}
