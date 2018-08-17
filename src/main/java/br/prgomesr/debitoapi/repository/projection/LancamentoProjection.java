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
    private String lote;

    public LancamentoProjection(Long id, String convenio, String cliente, BigDecimal valor, BigDecimal valorPago, LocalDate vencimento, LocalDate pagamento, Situacao situacao, String lote) {
        this.id = id;
        this.convenio = convenio;
        this.cliente = cliente;
        this.valor = valor;
        this.valorPago = valorPago;
        this.vencimento = vencimento;
        this.pagamento = pagamento;
        this.situacao = situacao;
        this.lote = lote;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getConvenio() {
        return convenio;
    }

    public void setConvenio(String convenio) {
        this.convenio = convenio;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public BigDecimal getValorPago() {
        return valorPago;
    }

    public void setValorPago(BigDecimal valorPago) {
        this.valorPago = valorPago;
    }

    public LocalDate getVencimento() {
        return vencimento;
    }

    public void setVencimento(LocalDate vencimento) {
        this.vencimento = vencimento;
    }

    public LocalDate getPagamento() {
        return pagamento;
    }

    public void setPagamento(LocalDate pagamento) {
        this.pagamento = pagamento;
    }

    public Situacao getSituacao() {
        return situacao;
    }

    public void setSituacao(Situacao situacao) {
        this.situacao = situacao;
    }

    public String getLote() {
        return lote;
    }

    public void setLote(String lote) {
        this.lote = lote;
    }

    @Override
    public String toString() {
        return "LancamentoProjection{" +
                "id=" + id +
                ", convenio='" + convenio + '\'' +
                ", cliente='" + cliente + '\'' +
                ", valor=" + valor +
                ", valorPago=" + valorPago +
                ", vencimento=" + vencimento +
                ", pagamento=" + pagamento +
                ", situacao=" + situacao +
                ", lote='" + lote + '\'' +
                '}';
    }
}
