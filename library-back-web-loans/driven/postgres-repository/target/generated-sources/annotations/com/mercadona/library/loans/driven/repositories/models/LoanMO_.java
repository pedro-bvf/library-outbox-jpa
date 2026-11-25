package com.mercadona.library.loans.driven.repositories.models;

import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.ListAttribute;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.UUID;

@StaticMetamodel(LoanMO.class)
@Generated("org.hibernate.processor.HibernateProcessor")
public abstract class LoanMO_ extends com.mercadona.library.loans.driven.repositories.models.AuditableEntity_ {

	public static final String LOAN_DETAILS = "loanDetails";
	public static final String BOOKING_EXPIRATION_DATE = "bookingExpirationDate";
	public static final String BOOKING_DATE = "bookingDate";
	public static final String ID = "id";
	public static final String PUBLIC_ID = "publicId";
	public static final String CUSTOMER = "customer";
	public static final String RETURNED_DATE = "returnedDate";

	
	/**
	 * @see com.mercadona.library.loans.driven.repositories.models.LoanMO#loanDetails
	 **/
	public static volatile ListAttribute<LoanMO, LoanDetailsMO> loanDetails;
	
	/**
	 * @see com.mercadona.library.loans.driven.repositories.models.LoanMO#bookingExpirationDate
	 **/
	public static volatile SingularAttribute<LoanMO, LocalDate> bookingExpirationDate;
	
	/**
	 * @see com.mercadona.library.loans.driven.repositories.models.LoanMO#bookingDate
	 **/
	public static volatile SingularAttribute<LoanMO, OffsetDateTime> bookingDate;
	
	/**
	 * @see com.mercadona.library.loans.driven.repositories.models.LoanMO#id
	 **/
	public static volatile SingularAttribute<LoanMO, UUID> id;
	
	/**
	 * @see com.mercadona.library.loans.driven.repositories.models.LoanMO
	 **/
	public static volatile EntityType<LoanMO> class_;
	
	/**
	 * @see com.mercadona.library.loans.driven.repositories.models.LoanMO#publicId
	 **/
	public static volatile SingularAttribute<LoanMO, Long> publicId;
	
	/**
	 * @see com.mercadona.library.loans.driven.repositories.models.LoanMO#customer
	 **/
	public static volatile SingularAttribute<LoanMO, CustomerMO> customer;
	
	/**
	 * @see com.mercadona.library.loans.driven.repositories.models.LoanMO#returnedDate
	 **/
	public static volatile SingularAttribute<LoanMO, OffsetDateTime> returnedDate;

}

