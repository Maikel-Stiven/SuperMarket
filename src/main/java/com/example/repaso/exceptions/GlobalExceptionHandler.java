package com.example.repaso.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice 
public class GlobalExceptionHandler {

   
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorHandler> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errores = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String campoNombre = ((FieldError) error).getField();
            String errorMensaje = error.getDefaultMessage();
            errores.put(campoNombre, errorMensaje);
        });

        ErrorHandler respuestaError = new ErrorHandler(
                HttpStatus.BAD_REQUEST.value(),
                "Error de validación en los datos de entrada",
                LocalDateTime.now(),
                errores
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(respuestaError);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorHandler> handleRuntimeException(RuntimeException excepcion) {
        ErrorHandler errorRespuesta = new ErrorHandler(
                HttpStatus.BAD_REQUEST.value(),
                excepcion.getMessage(),
                LocalDateTime.now()
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorRespuesta);
    }
}