package br.prgomesr.debitoapi.util.remessa.bb;

import org.jrimum.texgit.Record;

import java.math.BigDecimal;
import java.util.Date;

public class Transacao {

    private Record record;

    public Transacao(Record record) {
        if (record != null) {
            this.record = record;
        } else {
            throw new IllegalArgumentException("Registro de transação não informado");
        }
    }

    public String getCodRegistro() {
        return record.getValue("E01-CodRegistro");
    }

    public String getIdClienteEmpresa() {
        return record.getValue("E02-IdClienteEmpresa");
    }

    public String getAgenciaParaDebito() {
        return record.getValue("E03-AgenciaParaDebito");
    }

    public String getIdClienteBanco() {
        return record.getValue("E04-IdClienteBanco");
    }

    public Date getDataVencimento() {
        return record.getValue("E05-DataVencimento");
    }

    public BigDecimal getValorDoDebito() {
        return record.getValue("E06-ValorDoDebito");
    }

    public String getCodMoeda() {
        return record.getValue("E07-CodMoeda");
    }

    public String getUsoEmpresa() {
        return record.getValue("E08-UsoEmpresa");
    }

    public String getReservadoFuturo() {
        return record.getValue("E09-ReservadoFuturo");
    }

    public String getCodMovimento() {
        return record.getValue("E10-CodMovimento");
    }
}
