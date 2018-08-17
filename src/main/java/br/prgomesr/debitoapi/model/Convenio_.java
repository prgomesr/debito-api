package br.prgomesr.debitoapi.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Convenio.class)
public abstract class Convenio_ {

	public static volatile SingularAttribute<Convenio, Long> sequencial;
	public static volatile SingularAttribute<Convenio, String> numero;
	public static volatile SingularAttribute<Convenio, Conta> conta;
	public static volatile SingularAttribute<Convenio, Long> id;

}

