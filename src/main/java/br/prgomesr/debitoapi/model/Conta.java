package br.prgomesr.debitoapi.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "conta")
public class Conta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String agencia;

    @Column(name = "digito_agencia")
    private String digtoAgencia;

    private String conta;

    @Column(name = "digito_conta")
    private String digtoConta;

    @ManyToOne
    @JoinColumn(name = "convenio_id")
    private Convenio convenio;

    @Enumerated(EnumType.STRING)
    private Banco banco;

    @ManyToOne
    @JoinColumn(name = "empresa_id")
    private Empresa empresa;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAgencia() {
        return agencia;
    }

    public void setAgencia(String agencia) {
        this.agencia = agencia;
    }

    public String getDigtoAgencia() {
        return digtoAgencia;
    }

    public void setDigtoAgencia(String digtoAgencia) {
        this.digtoAgencia = digtoAgencia;
    }

    public String getConta() {
        return conta;
    }

    public void setConta(String conta) {
        this.conta = conta;
    }

    public String getDigtoConta() {
        return digtoConta;
    }

    public void setDigtoConta(String digtoConta) {
        this.digtoConta = digtoConta;
    }

    public Convenio getConvenio() {
        return convenio;
    }

    public void setConvenio(Convenio convenio) {
        this.convenio = convenio;
    }

    public Banco getBanco() {
        return banco;
    }

    public void setBanco(Banco banco) {
        this.banco = banco;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Conta conta = (Conta) o;
        return Objects.equals(id, conta.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
