package com.example.loginauthjwt.exception;

import com.example.loginauthjwt.security.jwt.AuthEntryPointJwt;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
//    private static final Logger logger = LoggerFactory.getLogger(AuthEntryPointJwt.class);

    //Handle bad request và trả lại qua API cho người dùng
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        BindingResult result = ex.getBindingResult();
        StringBuilder errorMessage = new StringBuilder();
        result.getFieldErrors().forEach(fieldError ->
            errorMessage.append(fieldError.getDefaultMessage()).append(". ")
        );
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

    // handle null exception
//    @ExceptionHandler(NullPointerException.class)
//    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
//    public ResponseEntity<String> handleNullPointerException(NullPointerException ex) {
//        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//                .body("NullPointerException occurred: " + ex.getMessage()+  "<=====>" + ex.getLocalizedMessage());
//    }
}
