package br.prgomesr.debitoapi.dto;

import br.prgomesr.debitoapi.model.Cliente;
import br.prgomesr.debitoapi.model.Situacao;

import java.math.BigDecimal;

public class LancamentoRecebidoEstatisticaCliente {

    private Cliente cliente;
    private String contato;
    private String recado;
    private BigDecimal valor;
    private Situacao situacao;
    private BigDecimal total;

    public LancamentoRecebidoEstatisticaCliente(Cliente cliente, String contato, String recado, BigDecimal valor, Situacao situacao, BigDecimal total) {
        this.cliente = cliente;
        this.contato = contato;
        this.recado = recado;
        this.valor = valor;
        this.situacao = situacao;
        this.total = total;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public String getContato() {
        return contato;
    }

    public void setContato(String contato) {
        this.contato = contato;
    }

    public String getRecado() {
        return recado;
    }

    public void setRecado(String recado) {
        this.recado = recado;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public Situacao getSituacao() {
        return situacao;
    }

    public void setSituacao(Situacao situacao) {
        this.situacao = situacao;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }
}
