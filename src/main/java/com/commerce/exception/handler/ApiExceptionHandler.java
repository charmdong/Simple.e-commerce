package com.commerce.exception.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestControllerAdvice(basePackages = {"com.commerce.api"})
public class ApiExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<Map<String, Object>> exceptionHandler(RuntimeException e) {
        Map<String, Object> result = new HashMap<>();

        result.put("exception", e.getClass());
        result.put("message", e.getMessage());

        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
