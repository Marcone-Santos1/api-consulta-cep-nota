package com.fatec.web.models;

public class Endereco {

    private String cep;
    private String logradouro;
    private String cidade;

    public Endereco(String cep, String logradouro, String cidade) {
        this.cep = cep;
        this.logradouro = logradouro;
        this.cidade = cidade;
    }

    public String getCep() {
        return cep;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public String getCidade() {
        return cidade;
    }
}
