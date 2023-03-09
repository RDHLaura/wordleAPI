package com.example.wordle.error;


import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.sql.SQLException;

@RestControllerAdvice
public class GlobalControllerAdvice extends ResponseEntityExceptionHandler {
    @ExceptionHandler(JuegoNotFoundException.class)
    public ResponseEntity<APIError> handleJuegoNotFound(JuegoNotFoundException exception){
        APIError apiError = new APIError(HttpStatus.NOT_FOUND, exception.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiError);
    }

    @ExceptionHandler(JugadorNotFoundException.class)
    public ResponseEntity<APIError> handleJugadorNotFound(JugadorNotFoundException exception){
        APIError apiError = new APIError(HttpStatus.NOT_FOUND, exception.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiError);
    }

    @ExceptionHandler(EquipoNotFoundException.class)
    public ResponseEntity<APIError> handleJugadorNotFound(EquipoNotFoundException exception){
        APIError apiError = new APIError(HttpStatus.NOT_FOUND, exception.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiError);
    }

    @ExceptionHandler(PartidaNotFoundException.class)
    public ResponseEntity<APIError> handlePartidaNotFound(PartidaNotFoundException exception){
        APIError apiError = new APIError(HttpStatus.NOT_FOUND, exception.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiError);
    }

    @ExceptionHandler(SQLException.class)
    public ResponseEntity<Object> handleSQLError(SQLException exception, WebRequest request){

        APIError apiError = new APIError(HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(apiError);
    }

    @Override
    public ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers,
                                                          HttpStatusCode statusCode, WebRequest request){
        APIError apiError = new APIError(statusCode, ex.getMessage());
        return ResponseEntity.status(statusCode).body(apiError);
    }
}
