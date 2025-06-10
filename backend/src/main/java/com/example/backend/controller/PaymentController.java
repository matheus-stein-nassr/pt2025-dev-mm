package com.example.backend.controller;

import com.example.backend.dto.PaymentRequestDTO;
import com.example.backend.dto.PaymentResponseDTO;
import com.example.backend.exception.ValidationException;
import com.example.backend.service.PaymentService;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3000") // liberando CORS para o front local
@RestController
@RequestMapping("/api")
public class PaymentController {

    private final PaymentService servicoPagamento;

    public PaymentController(PaymentService servicoPagamento) {
        this.servicoPagamento = servicoPagamento;
    }

    @PostMapping("/payments")
    public ResponseEntity<PaymentResponseDTO> processarPagamento(
            @Valid @RequestBody PaymentRequestDTO requisicaoPagamento) {
        try {
            PaymentResponseDTO resposta = servicoPagamento.processarPagamento(requisicaoPagamento);
            return ResponseEntity.ok(resposta);
        } catch (ValidationException ex) {
            PaymentResponseDTO respostaErro = new PaymentResponseDTO(
                    HttpStatus.BAD_REQUEST.value(),
                    ex.getMessage(),
                    null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(respostaErro);
        } catch (Exception ex) {
            PaymentResponseDTO respostaErro = new PaymentResponseDTO(
                    HttpStatus.INTERNAL_SERVER_ERROR.value(),
                    "Erro interno no servidor.",
                    null);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(respostaErro);
        }
    }
}