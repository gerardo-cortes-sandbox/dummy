package com.gerardocortes.dummy.controller;

import com.gerardocortes.dummy.controller.exception.NotFoundException;
import com.gerardocortes.dummy.controller.model.FailedResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;

import java.util.stream.Collectors;

@Slf4j
@RestControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<FailedResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex, ServletWebRequest req) {
        log.debug("Exception on {} with message: {}", req.getContextPath(), ex.getMessage(), ex);

        final String violations = ex.getBindingResult().getFieldErrors().stream()
                .map(fe -> fe.getField() + ": " + fe.getDefaultMessage() )
                .collect(Collectors.joining("\n", "Violations:\n", ""));

        return ResponseEntity
                .status(HttpStatus.UNPROCESSABLE_ENTITY)
                .body(new FailedResponse(violations));
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<FailedResponse> handleClientException(NotFoundException ex, ServletWebRequest req) {
        log.debug("Exception on {} with message: {}", req.getContextPath(), ex.getMessage(), ex);
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new FailedResponse(ex.getMessage()));
    }
}
