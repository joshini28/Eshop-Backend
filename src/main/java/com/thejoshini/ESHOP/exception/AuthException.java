package com.thejoshini.ESHOP.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class AuthException extends RuntimeException {
    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    private HttpStatus status;
    private String message;  // Fix spelling here

    public AuthException(HttpStatus status, String message) {  // Fix spelling in constructor
        this.message = message;  // Fix spelling here
        this.status = status;
    }
}
