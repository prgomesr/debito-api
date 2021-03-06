package br.prgomesr.debitoapi.dto;

import br.prgomesr.debitoapi.model.Cliente;

import java.math.BigDecimal;

public class LancamentoRecebidoEstatisticaCliente {

    private Cliente cliente;
    private Long codigoRetorno;
    private BigDecimal valorPago;

    public LancamentoRecebidoEstatisticaCliente(Cliente cliente, Long codigoRetorno, BigDecimal valorPago) {
        this.cliente = cliente;
        this.codigoRetorno = codigoRetorno;
        this.valorPago = valorPago;
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

    public BigDecimal getValorPago() {
        return valorPago;
    }

    public void setValorPago(BigDecimal valorPago) {
        this.valorPago = valorPago;
    }
}
