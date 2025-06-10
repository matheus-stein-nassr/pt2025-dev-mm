package com.example.backend.dto;

public class PaymentResponseDTO {

    private int codigo;
    private String mensagem;
    private Object dados;

    public PaymentResponseDTO() {
    }

    public PaymentResponseDTO(int codigo, String mensagem, Object dados) {
        this.codigo = codigo;
        this.mensagem = mensagem;
        this.dados = dados;
    }

    // Getters and Setters
    public int getcodigo() {
        return codigo;
    }

    public void setcodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public Object getDados() {
        return dados;
    }

    public void setDados(Object dados) {
        this.dados = dados;
    }
}
