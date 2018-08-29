package br.prgomesr.debitoapi.repository.filter;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public class LancamentoFilter {

    private Long convenio;

    private Long codigoRetorno;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate vencimento;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate vencimentoDe;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate vencimentoAte;

    public Long getConvenio() {
        return convenio;
    }

    public void setConvenio(Long convenio) {
        this.convenio = convenio;
    }

    public LocalDate getVencimentoDe() {
        return vencimentoDe;
    }

    public void setVencimentoDe(LocalDate vencimentoDe) {
        this.vencimentoDe = vencimentoDe;
    }

    public LocalDate getVencimentoAte() {
        return vencimentoAte;
    }

    public void setVencimentoAte(LocalDate vencimentoAte) {
        this.vencimentoAte = vencimentoAte;
    }

    public LocalDate getVencimento() {
        return vencimento;
    }

    public void setVencimento(LocalDate vencimento) {
        this.vencimento = vencimento;
    }

    public Long getCodigoRetorno() {
        return codigoRetorno;
    }

    public void setCodigoRetorno(Long codigoRetorno) {
        this.codigoRetorno = codigoRetorno;
    }
}
