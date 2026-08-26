package com.Ducat.book_service.Exception;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.Ducat.book_service.DTO.GenericResponseDTO;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = RuntimeException.class)
    public ResponseEntity<GenericResponseDTO<Map<String,String>>> handleRuntimeException(RuntimeException e){
        Map<String,String> error=Map.of("message","runtime exception aa gya hai ");
        GenericResponseDTO<Map<String,String>> responseDto=GenericResponseDTO.buildGenericResponse(error, "runtime exception ", 500);

        return ResponseEntity.status(500).body(responseDto);
    }

     @ExceptionHandler(value = Exception.class)
    public ResponseEntity<GenericResponseDTO<Map<String,String>>> handleRuntimeException(Exception e){
        Map<String,String> error=Map.of("message","runtime exception aa gya hai ");
        GenericResponseDTO<Map<String,String>> responseDto=GenericResponseDTO.buildGenericResponse(error, "Compile Time  exception ", 500);

        return ResponseEntity.status(500).body(responseDto);
    }
}
