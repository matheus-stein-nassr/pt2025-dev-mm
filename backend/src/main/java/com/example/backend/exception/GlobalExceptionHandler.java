package com.example.backend.exception;

import com.example.backend.dto.PaymentResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<PaymentResponseDTO> tratarExcecaoValidacao(ValidationException ex) {
        PaymentResponseDTO resposta = new PaymentResponseDTO(
                HttpStatus.BAD_REQUEST.value(),
                ex.getMessage(),
                null);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resposta);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<PaymentResponseDTO> tratarValidacaoCampos(MethodArgumentNotValidException ex) {
        Map<String, String> erros = new HashMap<>();
        for (FieldError erroCampo : ex.getBindingResult().getFieldErrors()) {
            erros.put(erroCampo.getField(), erroCampo.getDefaultMessage());
        }

        PaymentResponseDTO resposta = new PaymentResponseDTO(
                HttpStatus.BAD_REQUEST.value(),
                "Dados inválidos.",
                erros);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resposta);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<PaymentResponseDTO> tratarExcecaoGenerica(Exception ex) {
        PaymentResponseDTO resposta = new PaymentResponseDTO(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "Ocorreu um erro inesperado. Tente novamente mais tarde.",
                null);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(resposta);
    }
}