package com.example.loginauthjwt.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.naming.AuthenticationException;


@RestControllerAdvice
public class GlobalExceptionHandler {

    //Handle bad request và trả lại qua API cho người dùng
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        BindingResult result = ex.getBindingResult();
        StringBuilder errorMessage = new StringBuilder();
        result.getFieldErrors().forEach(fieldError -> {
            errorMessage.append(fieldError.getDefaultMessage()).append(". ");
        });
        return ResponseEntity.badRequest().body(errorMessage.toString());
    }


    // handle role exception (nhung no không chay dc)
    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<String> handleAuthenticationException(AuthenticationException ex) {
        if (ex.getCause() instanceof BadCredentialsException) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("You dont have permission");
        } else if (ex.getCause() instanceof DisabledException) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Your account is disabled");
        }
        // Handle other authentication exceptions as needed
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Authentication failed");
    }
}
