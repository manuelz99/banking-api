package com.manuelz.bankingapi.exceptions;

import com.manuelz.bankingapi.dtos.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CuentaDuplicadaEx.class)
    public ResponseEntity<ErrorResponse> manejarCuentaDuplicada(CuentaDuplicadaEx ex) {
        ErrorResponse errorResponse = ErrorResponse.builder().
                mensaje(ex.getMessage())
                .fecha(LocalDateTime.now()).
                build();
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }
}
