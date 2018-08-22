package br.prgomesr.debitoapi.util.retorno.bb;

import org.jrimum.texgit.Record;

import java.math.BigDecimal;

public class Sumario {

    private Record record;

    public Sumario(Record record) {
        if (record != null) {
            this.record = record;
        } else {
            throw new IllegalArgumentException("Registro de sumário não informado");
        }
    }


    public String getTotalRegistros() {
        return record.getValue("TotalRegistros");
    }

    public BigDecimal getValorTotalRegistros() {
        return record.getValue("ValorTotalRegistros");
    }

}
