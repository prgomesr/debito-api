package br.prgomesr.debitoapi.util.remessa.bb;

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

    public String getCodigoDoRegistro(){
        return record.getValue("CodigoDoRegistro");
    }

    public String getCodigoDeRemessa(){
        return record.getValue("CodigoDeRemessa");
    }

    public String getCodigoDoConvenio(){
        return record.getValue("CodigoDoConvenio");
    }

    public String getNomeDaEmpresa(){
        return record.getValue("NomeDaEmpresa");
    }

    public String getCodigoDoBanco(){
        return record.getValue("CodigoDoBanco");
    }

    public String getNomeDoBanco(){
        return record.getValue("NomeDoBanco");
    }

    public Date getDataDeGerecao(){
        return record.getValue("DataDeGerecao");
    }

    public String getNumeroSequencial(){
        return record.getValue("NumeroSequencial");
    }

    public String getVersaoDoLayout(){
        return record.getValue("VersaoDoLayout");
    }

    public String getIdentificacaoDoServico(){
        return record.getValue("IdentificacaoDoServico");
    }

    public String getReservadoParaOFuturo(){
        return record.getValue("ReservadoParaOFuturo");
    }
}
