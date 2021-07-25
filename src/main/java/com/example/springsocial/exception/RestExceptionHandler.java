package com.example.springsocial.exception;

import com.example.springsocial.payload.response.GeneralResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.support.WebExchangeBindException;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class RestExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(RestExceptionHandler.class);

    private ResponseEntity<GeneralResponse> handleGeneralException (Exception exception, HttpStatus code) {
        logger.error(exception.getMessage());

        GeneralResponse response = new GeneralResponse();
        response.setCode(code.value());
        response.setTimestamp();
        response.setMessage(exception.getMessage());

        return new ResponseEntity<GeneralResponse>(response, code);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<GeneralResponse> handleBadRequestException(BadRequestException exception) {
        return handleGeneralException(exception, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<GeneralResponse> handleResourceNotFoundException(ResourceNotFoundException exception) {
        return handleGeneralException(exception, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(OAuth2AuthenticationProcessingException.class)
    public ResponseEntity<GeneralResponse> handleOAuth2AuthenticationProcessingException(OAuth2AuthenticationProcessingException exception) {
        return handleGeneralException(exception, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<GeneralResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        logger.error(exception.getMessage());

        List<ObjectError> objectErrors = exception.getBindingResult().getAllErrors();
        List<String> errors = new ArrayList<String>();
        objectErrors.forEach(objectError -> {
            FieldError fieldError = (FieldError) objectError;
            String errorString = fieldError.getField() + " " + fieldError.getDefaultMessage();
            errors.add(errorString);
        });

        GeneralResponse response = new GeneralResponse();
        response.setCode(HttpStatus.BAD_REQUEST.value());
        response.setTimestamp();
        response.setMessage("the request is invalid");
        response.setDetails(errors);

        return new ResponseEntity<GeneralResponse>(response, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<GeneralResponse> handleHttpMessageNotReadableException(HttpMessageNotReadableException exception) {
        return handleGeneralException(exception, HttpStatus.BAD_REQUEST);
    }
}
