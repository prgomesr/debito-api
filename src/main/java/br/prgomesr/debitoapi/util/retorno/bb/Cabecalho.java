package br.prgomesr.debitoapi.util.retorno.bb;

import org.jrimum.texgit.Record;

import java.util.Date;

public class Cabecalho {

    private Record record;

    public Cabecalho(Record record) {
        if (record != null) {
            this.record = record;
        } else {
            throw new IllegalArgumentException("Registro de cabeçalho não informado");
        }
    }

    public String getCodigoDeRemessa() {
        return record.getValue("CodigoDeRemessa");
    }

    public String getCodigoDoConvenio() {
        return record.getValue("CodigoDoConvenio");
    }

    public Date getDataDeGerecao() {
        return record.getValue("DataDeGerecao");
    }

    public String getNumeroSequencial() {
        return record.getValue("NumeroSequencial");
    }

}
