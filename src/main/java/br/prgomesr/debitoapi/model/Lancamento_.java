package br.prgomesr.debitoapi.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Lancamento.class)
public abstract class Lancamento_ {

	public static volatile SingularAttribute<Lancamento, Cliente> cliente;
	public static volatile SingularAttribute<Lancamento, Situacao> situacao;
	public static volatile SingularAttribute<Lancamento, String> lote;
	public static volatile SingularAttribute<Lancamento, BigDecimal> valor;
	public static volatile SingularAttribute<Lancamento, Convenio> convenio;
	public static volatile SingularAttribute<Lancamento, BigDecimal> valorPago;
	public static volatile SingularAttribute<Lancamento, Long> id;
	public static volatile SingularAttribute<Lancamento, LocalDate> vencimento;
	public static volatile SingularAttribute<Lancamento, LocalDate> pagamento;

}

