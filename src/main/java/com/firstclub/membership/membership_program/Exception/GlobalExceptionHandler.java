package com.firstclub.membership.membership_program.Exception;

import jakarta.persistence.OptimisticLockException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<APIErrorResponse> handleNotFound(
            ResourceNotFoundException ex
    ) {
        return build(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<APIErrorResponse> handleBusiness(
            BusinessException ex
    ) {
        return build(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<APIErrorResponse> handleValidation(
            MethodArgumentNotValidException ex
    ) {
        return build("Invalid request payload", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(OptimisticLockException.class)
    public ResponseEntity<APIErrorResponse> handleConcurrency(
            OptimisticLockException ex
    ) {
        return build(
                "Concurrent modification detected. Retry request.",
                HttpStatus.CONFLICT
        );
    }

    private ResponseEntity<APIErrorResponse> build(
            String message,
            HttpStatus status
    ) {
        return ResponseEntity.status(status)
                .body(APIErrorResponse.builder()
                        .message(message)
                        .status(status.value())
                        .timestamp(LocalDateTime.now())
                        .build());
    }
}
