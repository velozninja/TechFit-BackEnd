package com.TechFit.TechFit.handlers;

import com.TechFit.TechFit.exeptions.Exceptions;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExeptionHandler {
    @ExceptionHandler(Exceptions.NotFound.class)
    public ResponseEntity<String> handleNotFound(Exceptions e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());

    }
    @ExceptionHandler(Exceptions.AlreadyExist.class)
    public ResponseEntity<String> handleAlreadyExist(Exceptions.AlreadyExist e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());

    }
    @ExceptionHandler(Exceptions.BadRequest.class)
    public ResponseEntity<String> handleBadRequest(Exceptions.BadRequest
                                                               e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());

    }

}
