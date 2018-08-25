package br.prgomesr.debitoapi.edi.bb;

public enum CodigoRetorno {
    EFETUADO("00"), INSUFICIENCIA_FUNDOS("01"), CONTA_CORRENTE_NAO_CADASTRADA("02"),
    OUTRAS_RESTRICOES("04"), VALOR_EXCEDE_LIMITE("05"), AGENCIA_EM_ENCERRAMENTO("10"),
    VALOR_INVALIDO("12"), DATA_LANCAMENTO_INVALIDA("13"), AGENCIA_INVALIDA("14"),
    CONTA_CORRENTE_INVALIDA("15"), DATA_DEBITO_ANTERIOR_A_DO_PROCESSAMENTO("18"),
    SEM_CONTRATO("30"), DEBITO_EFETUADO_EM_DATA_DIFERENTE("31"), MANUTENCAO_DO_CADASTRO("96"),
    CANCELAMENTO_NAO_ENCONTRADO("97"), CANCELAMENTO_FORA_TEMPO_HABIL("98"),
    CANCELADO_POR_SOLICITACAO("99");

    private String descricao;

    CodigoRetorno(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
