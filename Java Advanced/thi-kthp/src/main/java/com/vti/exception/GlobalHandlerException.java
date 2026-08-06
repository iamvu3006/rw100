package com.vti.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalHandlerException {

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<?> businessExceptionHandler(
            BusinessException ex) {
        Map<String, String> errors = new HashMap<>();
        errors.put("status", "500");
        errors.put("message", ex.getMessage());

        return new ResponseEntity<>(errors, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)//409
    public ResponseEntity<?> dataIntegrityViolationHandler(DataIntegrityViolationException ex) {
        Map<String, String> errors = new HashMap<>();
        errors.put("status", "409");
        errors.put("message", "Không thể thực hiện vì dữ liệu này đang được sử dụng ở nơi khác (VD: món ăn đã có trong đơn hàng).");

        return new ResponseEntity<>(errors, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(AuthenticationException.class)//401
    public ResponseEntity<?> authEntryPoint(AuthenticationException e) {
        Map<String, String> errors = new HashMap<>();
        errors.put("status", "401");
        errors.put("message", e.getMessage());

        return new ResponseEntity<>(errors, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(AccessDeniedException.class)//403
    public ResponseEntity<?> authEntryPoint(AccessDeniedException e) {
        Map<String, String> errors = new HashMap<>();
        errors.put("status", "403");
        errors.put("message", e.getMessage());

        return new ResponseEntity<>(errors, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> exceptionHandler(
            Exception e) {
        Map<String, String> errors = new HashMap<>();
        errors.put("status", "500");
        errors.put("message", e.getMessage());

        return new ResponseEntity<>(errors, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}