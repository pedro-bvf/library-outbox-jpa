package com.mercadona.library.loans.driven.repositories.models;

import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.EmbeddableType;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import java.util.UUID;

@StaticMetamodel(LoanDetailsMOPk.class)
@Generated("org.hibernate.processor.HibernateProcessor")
public abstract class LoanDetailsMOPk_ {

	public static final String LOAN_ID = "loanId";
	public static final String BOOK_ID = "bookId";

	
	/**
	 * @see com.mercadona.library.loans.driven.repositories.models.LoanDetailsMOPk
	 **/
	public static volatile EmbeddableType<LoanDetailsMOPk> class_;
	
	/**
	 * @see com.mercadona.library.loans.driven.repositories.models.LoanDetailsMOPk#loanId
	 **/
	public static volatile SingularAttribute<LoanDetailsMOPk, UUID> loanId;
	
	/**
	 * @see com.mercadona.library.loans.driven.repositories.models.LoanDetailsMOPk#bookId
	 **/
	public static volatile SingularAttribute<LoanDetailsMOPk, UUID> bookId;

}

