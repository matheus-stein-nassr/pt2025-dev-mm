package com.example.backend.dto;

import jakarta.validation.constraints.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDate;

public class PaymentRequestDTO {

    @NotBlank(message = "Nome é obrigatório.")
    private String nomeCompleto;

    @NotBlank(message = "CPF é obrigatório.")
    @Pattern(regexp = "\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}", message = "CPF deve estar no formato XXX.XXX.XXX-XX.")
    private String cpf;

    @NotNull(message = "Data de nascimento é obrigatória.")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate dataNascimento;

    @NotBlank(message = "Número do cartão é obrigatório.")
    @Pattern(regexp = "\\d{4} \\d{4} \\d{4} \\d{4}", message = "Número do cartão deve estar no formato XXXX XXXX XXXX XXXX.")
    private String numeroCartao;

    @NotBlank(message = "Nome impresso no cartão é obrigatório.")
    private String nomeImpresso;

    @NotBlank(message = "Data de validade é obrigatória.")
    @Pattern(regexp = "(0[1-9]|1[0-2])/\\d{2}", message = "Validade deve estar no formato MM/YY.")
    private String validade;

    @NotBlank(message = "CVV é obrigatório.")
    @Pattern(regexp = "\\d{3}", message = "CVV deve conter 3 dígitos.")
    private String cvv;

    @NotBlank(message = "E-mail é obrigatório.")
    @Email(message = "E-mail inválido.")
    private String email;

    // Getters e Setters

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getNumeroCartao() {
        return numeroCartao;
    }

    public void setNumeroCartao(String numeroCartao) {
        this.numeroCartao = numeroCartao;
    }

    public String getNomeImpresso() {
        return nomeImpresso;
    }

    public void setNomeImpresso(String nomeImpresso) {
        this.nomeImpresso = nomeImpresso;
    }

    public String getValidade() {
        return validade;
    }

    public void setValidade(String validade) {
        this.validade = validade;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
