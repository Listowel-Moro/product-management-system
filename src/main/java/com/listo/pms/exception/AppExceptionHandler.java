package com.listo.pms.exception;

import com.listo.pms.util.ApiError;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDate;

@Hidden
@ControllerAdvice
public class AppExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Object> handleProductNotFound(NotFoundException ex, WebRequest request){
        ApiError apiError = new ApiError(ex.getMessage(), HttpStatus.NOT_FOUND, LocalDate.now());
        return new ResponseEntity<Object>(apiError, HttpStatus.NOT_FOUND);
    }
}
