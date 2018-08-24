package br.prgomesr.debitoapi.repository.projection;

import br.prgomesr.debitoapi.model.Situacao;

import java.math.BigDecimal;
import java.time.LocalDate;

public class LancamentoProjection {

    private Long id;
    private String convenio;
    private String cliente;
    private BigDecimal valor;
    private BigDecimal valorPago;
    private LocalDate vencimento;
    private LocalDate pagamento;
    private Situacao situacao;
    private Long codigoRetorno;

    public LancamentoProjection(Long id, String convenio, String cliente, BigDecimal valor, BigDecimal valorPago, LocalDate vencimento, LocalDate pagamento, Situacao situacao, Long codigoRetorno) {
        this.id = id;
        this.convenio = convenio;
        this.cliente = cliente;
        this.valor = valor;
        this.valorPago = valorPago;
        this.vencimento = vencimento;
        this.pagamento = pagamento;
        this.situacao = situacao;
        this.codigoRetorno = codigoRetorno;
    }

    public Long getId() {
        return id;
    }

    public String getConvenio() {
        return convenio;
    }

    public String getCliente() {
        return cliente;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public BigDecimal getValorPago() {
        return valorPago;
    }

    public LocalDate getVencimento() {
        return vencimento;
    }

    public LocalDate getPagamento() {
        return pagamento;
    }

    public Situacao getSituacao() {
        return situacao;
    }

    public Long getCodigoRetorno() {
        return codigoRetorno;
    }
}
