package com.tiendafriki.lista_deseos;

import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.*;
import com.tiendafriki.lista_deseos.dto.ErrorDTO; 
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.*;
import org.springframework.http.*;
import java.util.*;
import java.time.*;

@RestControllerAdvice
public class Manejador {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity <ErrorDTO> ErrorValidacion(MethodArgumentNotValidException ex, HttpServletRequest request) {
        Map <String, String> Errores = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->
            Errores.put(error.getField(), error.getDefaultMessage())
        );
        ErrorDTO error = new ErrorDTO(
            LocalDateTime.now(),
            400,
            "[+] Error : " + 400 + " Error En La Validacion [>_<] ... ",
            Errores,
            request.getRequestURI()
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity <ErrorDTO> ErrorSolicitud(HttpMessageNotReadableException ex, HttpServletRequest request) {
        ErrorDTO error = new ErrorDTO(
            LocalDateTime.now(),
            400,
            "[+] Error : " + 400 + " Error En La Solicitud [>_<] ... ",
            null,
            request.getRequestURI()
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity <ErrorDTO> ErrorNoEncontrado(NoSuchElementException ex, HttpServletRequest request) {
        ErrorDTO error = new ErrorDTO(
            LocalDateTime.now(),
            404,
            "[+] Error : " + 404 + " Error Recurso No Encontrado [>_<] ... ",
            null,
            request.getRequestURI()
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity <ErrorDTO> ErrorGeneral(Exception ex, HttpServletRequest request) {
        ErrorDTO error = new ErrorDTO(
            LocalDateTime.now(),
            500,
            "[+] Error : " + 500 + " Error Interno Del Servidor [>_<] ... ",
            null,
            request.getRequestURI()
        );
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }

}