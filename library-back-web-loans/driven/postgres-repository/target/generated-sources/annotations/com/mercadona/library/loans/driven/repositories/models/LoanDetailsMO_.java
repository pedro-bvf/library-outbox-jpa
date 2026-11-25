package com.mercadona.library.loans.driven.repositories.models;

import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(LoanDetailsMO.class)
@Generated("org.hibernate.processor.HibernateProcessor")
public abstract class LoanDetailsMO_ extends com.mercadona.library.loans.driven.repositories.models.AuditableEntity_ {

	public static final String LOAN = "loan";
	public static final String BOOK = "book";
	public static final String ID = "id";

	
	/**
	 * @see com.mercadona.library.loans.driven.repositories.models.LoanDetailsMO#loan
	 **/
	public static volatile SingularAttribute<LoanDetailsMO, LoanMO> loan;
	
	/**
	 * @see com.mercadona.library.loans.driven.repositories.models.LoanDetailsMO#book
	 **/
	public static volatile SingularAttribute<LoanDetailsMO, BookMO> book;
	
	/**
	 * @see com.mercadona.library.loans.driven.repositories.models.LoanDetailsMO#id
	 **/
	public static volatile SingularAttribute<LoanDetailsMO, LoanDetailsMOPk> id;
	
	/**
	 * @see com.mercadona.library.loans.driven.repositories.models.LoanDetailsMO
	 **/
	public static volatile EntityType<LoanDetailsMO> class_;

}

