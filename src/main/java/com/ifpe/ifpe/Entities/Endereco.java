package com.ifpe.ifpe.Entities;

public class Endereco {

    private String cep;
    private String logradouro;
    private String bairro;
    private String cidade;
    private String estado;
    private String numero;        // não preenchido automaticamente
    private String complemento;   // não preenchido automaticamente

    public Endereco(
            String cep, 
            String logradouro, 
            String bairro,
            String cidade, 
            String estado,
            String numero, 
            String complemento) {

        this.cep = cep;
        this.logradouro = logradouro;
        this.bairro = bairro;
        this.cidade = cidade;
        this.estado = estado;
        this.numero = numero;
        this.complemento = complemento;
    }

    public Endereco(
            String cep, 
            String logradouro,
            String bairro, 
            String cidade, 
            String estado) {

        this.cep = cep;
        this.logradouro = logradouro;
        this.bairro = bairro;
        this.cidade = cidade;
        this.estado = estado;
    }

    public String getCep() {
        return cep;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public String getBairro() {
        return bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public String getEstado() {
        return estado;
    }

    public String getNumero() {
        return numero;
    }

    public String getComplemento() {
        return complemento;
    }
}
