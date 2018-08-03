package br.prgomesr.debitoapi.model;

public enum Banco {
    BANCO_DO_BRASIL("Banco do Brasil"), SANTANDER("Santander"), BRADESCO("Bradesco");

    private String descricao;

    Banco(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
