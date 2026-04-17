package com.groupeisi.consultation_ms.exception;

import com.groupeisi.common_ms.exception.EntityExistsException;
import com.groupeisi.common_ms.exception.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleEntityNotFoundException(EntityNotFoundException e) {
        Map<String, String> error = new HashMap<>();
        error.put("erreur", e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(EntityExistsException.class)
    public ResponseEntity<Map<String, String>> handleEntityExistsException(EntityExistsException e) {
        Map<String, String> error = new HashMap<>();
        error.put("erreur", e.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
    }
}