package com.thejoshini.ESHOP.exception;

import com.thejoshini.ESHOP.payload.ErrorDetail;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    //Resource Not Fopund Exception
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorDetail> handleResourceNotFound(ResourceNotFoundException ex,
                                                              WebRequest request){
        ErrorDetail errorDetail= new ErrorDetail(
                new Date(),
                ex.getMessage(),
                request.getDescription(false)
        ) ;
        return new ResponseEntity<>(errorDetail, HttpStatus.NOT_FOUND);

    }
    @ExceptionHandler(AuthException.class)
    public ResponseEntity<ErrorDetail> handleAuthException(AuthException ex,WebRequest request){
        ErrorDetail errorDetail= new ErrorDetail(
                new Date(),
                ex.getMessage(),
                request.getDescription(false)
        );
        return new ResponseEntity<>(errorDetail,ex.getStatus());
    }
}
