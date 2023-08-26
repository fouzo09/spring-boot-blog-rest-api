package com.blogrestapi.app.exceptions;

import com.blogrestapi.app.payload.ErrorDetails;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<ErrorDetails> handleNullPointerException(NullPointerException nullPointerException,
                                                                        WebRequest webRequest){
        ErrorDetails errorDetails = new ErrorDetails(new Date(),
                nullPointerException.getMessage(),
                webRequest.getDescription(true));

        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorDetails> handleResourceNotFoundException(ResourceNotFoundException resourceNotFoundException,
                                                                         WebRequest webRequest){
        ErrorDetails errorDetails = new ErrorDetails(new Date(),
                resourceNotFoundException.getMessage(),
                webRequest.getDescription(true));

        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception,
                                                        WebRequest webRequest){
        Map<String, String> errors = new HashMap<>();
        exception.getBindingResult().getAllErrors().forEach((error)->{
            String name = ((FieldError)error).getField();
            String message = error.getDefaultMessage();
            errors.put(name, message);
        });


        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(SQLException.class)
    public ResponseEntity<ErrorDetails> handleSqlException(SQLException exception,
                                                     WebRequest webRequest){
        ErrorDetails errorDetails = new ErrorDetails(new Date(),
                exception.getMessage(),
                webRequest.getDescription(true));
        System.out.println(exception.getMessage());
        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDetails> handleGlobalException(Exception exception,
                                                              WebRequest webRequest){
        ErrorDetails errorDetails = new ErrorDetails(new Date(),
                exception.getMessage(),
                webRequest.getDescription(true));
        System.out.println(exception.getMessage());
        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }


}
