package br.prgomesr.debitoapi.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity
@Table(name = "convenio")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Convenio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String numero;

    @NotNull
    private Long sequencial;

    @ManyToOne
    @JoinColumn(name = "conta_id")
    @NotNull
    private Conta conta;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public Long getSequencial() {
        return sequencial;
    }

    public void setSequencial(Long sequencial) {
        this.sequencial = sequencial;
    }

    public Conta getConta() {
        return conta;
    }

    public void setConta(Conta conta) {
        this.conta = conta;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Convenio convenio = (Convenio) o;
        return Objects.equals(id, convenio.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Convenio{" +
                "id=" + id +
                ", numero='" + numero + '\'' +
                ", sequencial=" + sequencial +
                ", conta=" + conta +
                '}';
    }
}
