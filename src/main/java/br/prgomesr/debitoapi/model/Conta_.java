package br.prgomesr.debitoapi.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Conta.class)
public abstract class Conta_ {

	public static volatile SingularAttribute<Conta, String> digitoAgencia;
	public static volatile SingularAttribute<Conta, String> numero;
	public static volatile SingularAttribute<Conta, String> digitoConta;
	public static volatile SingularAttribute<Conta, Banco> banco;
	public static volatile SingularAttribute<Conta, Long> id;
	public static volatile SingularAttribute<Conta, Empresa> empresa;
	public static volatile SingularAttribute<Conta, String> agencia;

}

