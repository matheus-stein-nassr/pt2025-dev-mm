package com.example.backend.service;

import com.example.backend.dto.PaymentRequestDTO;
import com.example.backend.dto.PaymentResponseDTO;
import com.example.backend.exception.ValidationException;
import com.example.backend.utils.MaskUtils;
import com.example.backend.validator.CpfValidator;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;

@Service
public class PaymentService {

    private static final DateTimeFormatter FORMATADOR_VALIDADE = DateTimeFormatter.ofPattern("MM/yy");

    public PaymentResponseDTO processarPagamento(PaymentRequestDTO requisicao) {
        if (requisicao == null) {
            throw new ValidationException("Requisição inválida.");
        }

        if (requisicao.getCpf() == null || requisicao.getCpf().trim().isEmpty()) {
            throw new ValidationException("O campo 'CPF' é obrigatório e não pode estar vazio.");
        }

        String cpf = MaskUtils.removerMascara(requisicao.getCpf());

        if (!CpfValidator.ehValido(cpf)) {
            throw new ValidationException("O CPF informado '" + requisicao.getCpf() + "' é inválido.");
        }

        if (requisicao.getNumeroCartao() == null || requisicao.getNumeroCartao().trim().isEmpty()) {
            throw new ValidationException("O campo 'Número do cartão' é obrigatório e não pode estar vazio.");
        }

        String numeroCartao = MaskUtils.removerMascara(requisicao.getNumeroCartao());
        if (numeroCartao.length() < 13) {
            throw new ValidationException("O número do cartão informado possui menos de 13 dígitos.");
        }

        if (requisicao.getDataNascimento() == null) {
            throw new ValidationException("O campo 'Data de nascimento' é obrigatório.");
        }

        if (requisicao.getDataNascimento().isAfter(LocalDate.now())) {
            throw new ValidationException("A data de nascimento não pode ser no futuro.");
        }

        if (requisicao.getValidade() == null || requisicao.getValidade().trim().isEmpty()) {
            throw new ValidationException("Data de validade do cartão é obrigatória.");
        }

        if (!tem18AnosOuMais(requisicao.getDataNascimento())) {
            throw new ValidationException("É necessário ter pelo menos 18 anos.");
        }

        if (!cartaoValido(requisicao.getValidade())) {
            throw new ValidationException("Data de validade do cartão inválida ou expirada.");
        }

        String bandeira = detectarBandeiraCartao(numeroCartao);
        if (bandeira == null) {
            throw new ValidationException("Bandeira do cartão não suportada.");
        }

        return new PaymentResponseDTO(
                200,
                "Pagamento processado com sucesso.",
                "Bandeira detectada: " + bandeira);
    }

    private boolean tem18AnosOuMais(LocalDate dataNascimento) {
        try {
            return Period.between(dataNascimento, LocalDate.now()).getYears() >= 18;
        } catch (Exception e) {
            throw new ValidationException("Data de nascimento inválida.", e);
        }
    }

    private boolean cartaoValido(String validade) {
        try {
            YearMonth anoMes = YearMonth.parse(validade, FORMATADOR_VALIDADE);
            if (anoMes.atEndOfMonth().isBefore(LocalDate.now())) {
                throw new ValidationException("O cartão está expirado. Validade informada: " + validade);
            }
            return true;
        } catch (java.time.format.DateTimeParseException e) {
            throw new ValidationException(
                    "Formato de validade do cartão inválido. Use MM/yy. Valor recebido: " + validade, e);
        }
    }

    private String detectarBandeiraCartao(String numeroCartao) {
        if (numeroCartao == null || numeroCartao.length() < 4) {
            return null;
        }

        numeroCartao = numeroCartao.trim();

        if (numeroCartao.startsWith("4")) {
            return "Visa";
        }

        String doisDigitos = numeroCartao.substring(0, 2);
        if (doisDigitos.matches("5[1-5]")) {
            return "MasterCard";
        }

        int prefixo4 = Integer.parseInt(numeroCartao.substring(0, 4));
        if (prefixo4 >= 2221 && prefixo4 <= 2720) {
            return "MasterCard";
        }

        return null;
    }
}
