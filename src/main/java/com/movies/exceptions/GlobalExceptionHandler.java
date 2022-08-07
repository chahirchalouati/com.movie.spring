package com.movies.exceptions;

import com.movies.dtos.Responses.ErrorResponse;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

import static java.util.Objects.nonNull;
import static java.util.stream.Collectors.toMap;

/**
 * @author Chahir Chalouati
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler({EntityNotFoundException.class})
    public ResponseEntity<String> notFoundResponse(EntityNotFoundException exception) {
        return new ResponseEntity<>(exception.getLocalizedMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<?> badRequestResponse(MethodArgumentNotValidException exception) {
        final Map<String, String> errorsMap = exception.getBindingResult().getFieldErrors()
                .stream()
                .filter(fieldError -> !fieldError.getField().equals("roles"))
                .filter(field -> nonNull(field.getDefaultMessage()))
                .collect(toMap(FieldError::getField, FieldError::getDefaultMessage));
        return new ResponseEntity<>(new ErrorResponse(errorsMap), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({BindException.class})
    public ResponseEntity<?> bindException(BindException exception) {
        final Map<String, String> errorsMap = exception.getBindingResult().getFieldErrors()
                .stream()
                .filter(field -> nonNull(field.getDefaultMessage()))
                .collect(toMap(FieldError::getField, FieldError::getDefaultMessage));
        return new ResponseEntity<>(new ErrorResponse(errorsMap), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({FileStoreException.class})
    public ResponseEntity<?> fileStoreError(FileStoreException exception) {
        return new ResponseEntity<>(new ErrorResponse(exception.getLocalizedMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler({FileFormatNotAcceptedException.class})
    public ResponseEntity<?> badRequest(FileFormatNotAcceptedException exception) {
        return new ResponseEntity<>(new ErrorResponse(exception.getLocalizedMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({SignatureException.class, MalformedJwtException.class, ExpiredJwtException.class, UnsupportedJwtException.class})
    public ResponseEntity<String> unauthorizedResponse() {
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

}
