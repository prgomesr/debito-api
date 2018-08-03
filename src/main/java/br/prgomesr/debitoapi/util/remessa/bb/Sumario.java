package br.prgomesr.debitoapi.util.remessa.bb;

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

    public String getCodRegistro() {
        return record.getValue("Z01-CodRegistro");
    }

    public String getTotalRegistros() {
        return record.getValue("Z02-TotalRegistros");
    }

    public BigDecimal getValorTotalRegistros() {
        return record.getValue("Z03-ValorTotalRegistros");
    }

    public String getReservadoFuturo() {
        return record.getValue("Z04-ReservadoFuturo");
    }
}
