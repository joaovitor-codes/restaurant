package com.facol.restaurant.config;

import com.facol.restaurant.exception.ExceptionResponse;
import com.facol.restaurant.exception.NotFoundException;
import com.facol.restaurant.exception.ReferentialIntegrityException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ExceptionResponse> handleNotFoundException(NotFoundException ex) {
        var exception = new ExceptionResponse(
                ex.getMessage(),
                HttpStatus.NOT_FOUND.name(),
                HttpStatus.NOT_FOUND.value()
        );

        return new ResponseEntity<>(exception, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ReferentialIntegrityException.class)
    public ResponseEntity<ExceptionResponse> handleReferentialIntegrityException(ReferentialIntegrityException ex) {
        var exception = new ExceptionResponse(
                ex.getMessage(),
                HttpStatus.CONFLICT.name(),
                HttpStatus.CONFLICT.value()
        );

        return new ResponseEntity<>(exception, HttpStatus.CONFLICT);
    }
}
