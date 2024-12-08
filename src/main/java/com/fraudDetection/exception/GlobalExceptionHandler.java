package com.fraudDetection.exception;

import com.fraudDetection.constants.StatementErrorMessages;
import com.fraudDetection.response.AccountStatusResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AxisException.class)
    public ResponseEntity<AccountStatusResponse> handleAxisException(AxisException ex) {
        AccountStatusResponse response = new AccountStatusResponse(
                StatementErrorMessages.INTERNAL_SERVER_ERROR.getStatus().value(),
                "E500",
                StatementErrorMessages.INTERNAL_SERVER_ERROR.getMessage() + ": " + ex.getMessage(),
                null // Set data to null in case of errors
        );

        return ResponseEntity.status(StatementErrorMessages.INTERNAL_SERVER_ERROR.getStatus())
                .body(response);    }

    // Handle missing request body
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<AccountStatusResponse> handleMissingRequestBody(HttpMessageNotReadableException ex) {
        AccountStatusResponse response = new AccountStatusResponse(
                StatementErrorMessages.BAD_REQUEST.getStatus().value(),
                "E400",
                "Required request body is missing",
                null
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }
}