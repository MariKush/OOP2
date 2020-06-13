package com.lab3.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ErrorHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(BookingNotFoundException.class)
    public ResponseEntity<Object> handlerBookingNotFoundException() {

        Map<String, Object> body = new HashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("message", "Booking not found");

        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);

    }

    @ExceptionHandler(ClassNotFoundException.class)
    public ResponseEntity<Object> handlerCarNotFoundException() {

        Map<String, Object> body = new HashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("message", "Car not found");

        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);

    }

    @ExceptionHandler(NullPasswordException.class)
    public ResponseEntity<Object> handlerNullPasswordException() {

        Map<String, Object> body = new HashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("message", "Null password");

        return new ResponseEntity<>(body, HttpStatus.CONFLICT);

    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Object> handlerUserNotFoundException() {

        Map<String, Object> body = new HashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("message", "User not found");

        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);

    }
}
