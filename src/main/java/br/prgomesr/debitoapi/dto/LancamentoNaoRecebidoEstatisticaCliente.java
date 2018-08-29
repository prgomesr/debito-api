package br.prgomesr.debitoapi.dto;

import br.prgomesr.debitoapi.model.Cliente;

import java.math.BigDecimal;

public class LancamentoNaoRecebidoEstatisticaCliente {

    private Cliente cliente;
    private Long codigoRetorno;
    private BigDecimal valor;

    public LancamentoNaoRecebidoEstatisticaCliente(Cliente cliente, Long codigoRetorno, BigDecimal valor) {
        this.cliente = cliente;
        this.codigoRetorno = codigoRetorno;
        this.valor = valor;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Long getCodigoRetorno() {
        return codigoRetorno;
    }

    public void setCodigoRetorno(Long codigoRetorno) {
        this.codigoRetorno = codigoRetorno;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }
}
