package br.prgomesr.debitoapi.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Cliente.class)
public abstract class Cliente_ {

	public static volatile SingularAttribute<Cliente, String> situacao;
	public static volatile SingularAttribute<Cliente, Boolean> ativo;
	public static volatile SingularAttribute<Cliente, String> identificadorBanco;
	public static volatile SingularAttribute<Cliente, String> telefone1;
	public static volatile SingularAttribute<Cliente, String> digitoAgencia;
	public static volatile SingularAttribute<Cliente, String> digitoConta;
	public static volatile SingularAttribute<Cliente, String> conta;
	public static volatile SingularAttribute<Cliente, Banco> banco;
	public static volatile SingularAttribute<Cliente, String> nome;
	public static volatile SingularAttribute<Cliente, Long> id;
	public static volatile SingularAttribute<Cliente, String> telefone2;
	public static volatile SingularAttribute<Cliente, String> agencia;

}

