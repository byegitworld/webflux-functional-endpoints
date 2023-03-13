package com.example.demo.error;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Error {
    INVALID_REQUEST(HttpStatus.BAD_REQUEST, "EC001", "Invalid Request"),
    DEAUTHENTIVATED_REQUEST(HttpStatus.UNAUTHORIZED, "EC002", "Deauthenticated Request"),
    DEAUTHORIZED_REQUEST(HttpStatus.FORBIDDEN, "EC003", "Deauthorized Request"),
    DATA_NOT_FOUND(HttpStatus.NOT_FOUND, "EC004", "Data Not Found"),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "EC999", "INTERNAL SERVER ERROR");
    
    private final HttpStatus status;
    private final String code;
    private final String message;
}
