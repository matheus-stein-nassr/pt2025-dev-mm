package com.example.backend.validator;

public class CpfValidator {

    public static boolean ehValido(String cpf) {
        if (cpf == null || cpf.length() != 11 || cpf.matches("(\\d)\\1{10}"))
            return false;

        try {
            int[] numeros = cpf.chars().map(c -> c - '0').toArray();

            int soma1 = 0, soma2 = 0;
            for (int i = 0; i < 9; i++) {
                soma1 += numeros[i] * (10 - i);
                soma2 += numeros[i] * (11 - i);
            }

            int digito1 = soma1 % 11 < 2 ? 0 : 11 - (soma1 % 11);
            soma2 += digito1 * 2;
            int digito2 = soma2 % 11 < 2 ? 0 : 11 - (soma2 % 11);

            return numeros[9] == digito1 && numeros[10] == digito2;
        } catch (Exception e) {
            return false;
        }
    }
}