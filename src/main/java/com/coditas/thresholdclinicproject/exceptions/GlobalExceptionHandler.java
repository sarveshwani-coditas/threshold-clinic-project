package com.coditas.thresholdclinicproject.exceptions;

import com.coditas.thresholdclinicproject.dto.ApplicationResponse;
import com.coditas.thresholdclinicproject.dto.ErrorResponse;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.nio.file.AccessDeniedException;
import java.time.Instant;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApplicationResponse<ErrorResponse>> handleResourceNotFound(ResourceNotFoundException ex, HttpServletRequest http) {

        ErrorResponse response = new ErrorResponse();
        response.setStatus(HttpStatus.NOT_FOUND.value());
        response.setMessage(ex.getMessage());
        response.setPath(http.getRequestURL().toString());
        response.setTimestamp(Instant.now());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                ApplicationResponse.<ErrorResponse>builder()
                        .success(false)
                        .message("Exception Occurred")
                        .errors(response)
                        .build()
        );
    }

    @ExceptionHandler(DuplicateResourceException.class)
    public ResponseEntity<ApplicationResponse<ErrorResponse>> handleDuplicateResource(DuplicateResourceException ex, HttpServletRequest http) {

        ErrorResponse response = new ErrorResponse();
        response.setStatus(HttpStatus.CONFLICT.value());
        response.setMessage(ex.getMessage());
        response.setPath(http.getRequestURL().toString());
        response.setTimestamp(Instant.now());

        return ResponseEntity.status(HttpStatus.CONFLICT).body(
                ApplicationResponse.<ErrorResponse>builder()
                        .success(false)
                        .message("Exception occurred")
                        .errors(response)
                        .build()
        );
    }

    @ExceptionHandler(InvalidCredentialsException.class)
    public ResponseEntity<ApplicationResponse<ErrorResponse>> handleInvalidCredentials(InvalidCredentialsException ex, HttpServletRequest http) {

        ErrorResponse response = new ErrorResponse();
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.setMessage(ex.getMessage());
        response.setPath(http.getRequestURL().toString());
        response.setTimestamp(Instant.now());

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(
                ApplicationResponse.<ErrorResponse>builder()
                        .success(false)
                        .message("Exception occurred")
                        .errors(response)
                        .build()
        );
    }

    @ExceptionHandler(UnAuthenticatedUserException.class)
    public ResponseEntity<ApplicationResponse<ErrorResponse>> handleUnAuthenticatedUser(UnAuthenticatedUserException ex, HttpServletRequest http) {

        ErrorResponse response = new ErrorResponse();
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.setMessage(ex.getMessage());
        response.setPath(http.getRequestURL().toString());
        response.setTimestamp(Instant.now());

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(
                ApplicationResponse.<ErrorResponse>builder()
                        .success(false)
                        .message("Exception occurred")
                        .errors(response)
                        .build()
        );
    }


    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public ResponseEntity<ApplicationResponse<ErrorResponse>> handleRequestMethodNotSupported(HttpMediaTypeNotSupportedException ex, HttpServletRequest http) {
        ErrorResponse response = new ErrorResponse();
        response.setStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE.value());
        response.setMessage(ex.getMessage());
        response.setPath(http.getRequestURL().toString());
        response.setTimestamp(Instant.now());

        return ResponseEntity.status(HttpStatus.UNSUPPORTED_MEDIA_TYPE).body(
                ApplicationResponse.<ErrorResponse>builder()
                        .success(false)
                        .message("Please Enter the request in compatible type")
                        .errors(response)
                        .build()
        );
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ApplicationResponse<ErrorResponse>> handleRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex, HttpServletRequest http) {
        ErrorResponse response = new ErrorResponse();
        response.setStatus(HttpStatus.FORBIDDEN.value());
        response.setMessage(ex.getMessage());
        response.setPath(http.getRequestURL().toString());
        response.setTimestamp(Instant.now());

        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(
                ApplicationResponse.<ErrorResponse>builder()
                        .success(false)
                        .message("Please check request method type")
                        .errors(response)
                        .build()
        );
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ApplicationResponse<ErrorResponse>> handleForbiddenException(AccessDeniedException ex, HttpServletRequest http) {
        ErrorResponse response = new ErrorResponse();
        response.setStatus(HttpStatus.FORBIDDEN.value());
        response.setMessage(ex.getMessage());
        response.setPath(http.getRequestURL().toString());
        response.setTimestamp(Instant.now());

        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(
                ApplicationResponse.<ErrorResponse>builder()
                        .success(false)
                        .message("you are not authorized to access this feature")
                        .errors(response)
                        .build()
        );
    }

    @ExceptionHandler(ExpiredJwtException.class)
    public ResponseEntity<ApplicationResponse<ErrorResponse>> handleExpiredJwtException(ExpiredJwtException ex, HttpServletRequest http) {
        ErrorResponse response = new ErrorResponse();
        response.setStatus(HttpStatus.FORBIDDEN.value());
        response.setMessage(ex.getMessage());
        response.setPath(http.getRequestURL().toString());
        response.setTimestamp(Instant.now());

        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(
                ApplicationResponse.<ErrorResponse>builder()
                        .success(false)
                        .message("your jwt token is expired")
                        .errors(response)
                        .build()
        );
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApplicationResponse<ErrorResponse>> handleForbiddenException(MethodArgumentNotValidException ex, HttpServletRequest http) {
        ErrorResponse response = new ErrorResponse();
        response.setStatus(HttpStatus.BAD_REQUEST.value());
        response.setMessage(ex.getMessage());
        response.setPath(http.getRequestURL().toString());
        response.setTimestamp(Instant.now());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                ApplicationResponse.<ErrorResponse>builder()
                        .success(false)
                        .message("Validation error occurred, please check your request")
                        .errors(response)
                        .build()
        );
    }

}
