package br.prgomesr.debitoapi.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Remessa.class)
public abstract class Remessa_ {

	public static volatile SingularAttribute<Remessa, String> situacao;
	public static volatile SingularAttribute<Remessa, LocalDate> data;
	public static volatile SingularAttribute<Remessa, BigDecimal> valor;
	public static volatile SingularAttribute<Remessa, String> nome;
	public static volatile SingularAttribute<Remessa, Long> id;

}

