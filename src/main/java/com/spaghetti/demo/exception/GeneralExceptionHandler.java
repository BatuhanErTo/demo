package com.spaghetti.demo.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;

@RestControllerAdvice
public class GeneralExceptionHandler extends ResponseEntityExceptionHandler {
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        HashMap<String, String> errors = new HashMap<>();

        ex.getBindingResult().getAllErrors()
                .forEach(objectError -> {
                    String fieldName = ((FieldError) objectError).getField();
                    String errorName = objectError.getDefaultMessage();
                    errors.put(fieldName,errorName);
                });
        return new ResponseEntity<>(errors,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CustomerNotFoundException.class)
    public ResponseEntity<?> customerNotFoundException(CustomerNotFoundException exception){
        return new ResponseEntity<>(exception.getLocalizedMessage(), HttpStatus.NOT_FOUND);
    }
}
