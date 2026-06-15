package com.TechFit.TechFit.handlers;

import com.TechFit.TechFit.exeptions.AlreadyExist;
import com.TechFit.TechFit.exeptions.NotFound;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExeptionHandler {
    @ExceptionHandler(NotFound.class)
    public ResponseEntity<String> handleNotFound(NotFound e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());

    }
    @ExceptionHandler(AlreadyExist.class)
    public ResponseEntity<String> handleAlreadyExist(AlreadyExist e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());

    }

}
