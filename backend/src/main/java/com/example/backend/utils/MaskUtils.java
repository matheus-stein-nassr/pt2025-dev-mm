package com.example.backend.utils;

/*
 * Esta classe fornece métodos para remover caracteres de formatação (máscaras)
 * de strings, retornando apenas os dígitos numéricos.
 */
public class MaskUtils {

    public static String removerMascara(String entrada) {
        if (entrada == null)
            return null;
        return entrada.replaceAll("[^\\d]", "");
    }
}
