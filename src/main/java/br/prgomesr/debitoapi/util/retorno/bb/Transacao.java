package br.prgomesr.debitoapi.util.retorno.bb;

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

    public String getIdClienteEmpresa() {
        return record.getValue("IdClienteEmpresa");
    }

    public Date getDataVencimento() {
        return record.getValue("DataVencimento");
    }

    public BigDecimal getValorDoDebito() {
        return record.getValue("ValorDoDebito");
    }

    public String getCodRetorno() {
        return record.getValue("CodRetorno");
    }

}
