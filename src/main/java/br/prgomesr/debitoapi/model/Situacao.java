package br.prgomesr.debitoapi.model;

public enum Situacao {
    PENDENTE("PENDENTE"), EM_ABERTO("EM ABERTO"), VENCIDO("VENCIDO");

    private String descricao;

    Situacao(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
