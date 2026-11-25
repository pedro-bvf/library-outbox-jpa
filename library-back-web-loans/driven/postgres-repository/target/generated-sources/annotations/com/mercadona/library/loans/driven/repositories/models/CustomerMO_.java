package com.mercadona.library.loans.driven.repositories.models;

import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.ListAttribute;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import java.time.LocalDate;
import java.util.UUID;

@StaticMetamodel(CustomerMO.class)
@Generated("org.hibernate.processor.HibernateProcessor")
public abstract class CustomerMO_ {

	public static final String BIRTHDAY = "birthday";
	public static final String ADDRESS = "address";
	public static final String PHONE = "phone";
	public static final String LOANS = "loans";
	public static final String NAME = "name";
	public static final String ID = "id";
	public static final String CARD_IMAGE_URL = "cardImageUrl";
	public static final String PUBLIC_ID = "publicId";

	
	/**
	 * @see com.mercadona.library.loans.driven.repositories.models.CustomerMO#birthday
	 **/
	public static volatile SingularAttribute<CustomerMO, LocalDate> birthday;
	
	/**
	 * @see com.mercadona.library.loans.driven.repositories.models.CustomerMO#address
	 **/
	public static volatile SingularAttribute<CustomerMO, String> address;
	
	/**
	 * @see com.mercadona.library.loans.driven.repositories.models.CustomerMO#phone
	 **/
	public static volatile SingularAttribute<CustomerMO, Long> phone;
	
	/**
	 * @see com.mercadona.library.loans.driven.repositories.models.CustomerMO#loans
	 **/
	public static volatile ListAttribute<CustomerMO, LoanMO> loans;
	
	/**
	 * @see com.mercadona.library.loans.driven.repositories.models.CustomerMO#name
	 **/
	public static volatile SingularAttribute<CustomerMO, String> name;
	
	/**
	 * @see com.mercadona.library.loans.driven.repositories.models.CustomerMO#id
	 **/
	public static volatile SingularAttribute<CustomerMO, UUID> id;
	
	/**
	 * @see com.mercadona.library.loans.driven.repositories.models.CustomerMO#cardImageUrl
	 **/
	public static volatile SingularAttribute<CustomerMO, String> cardImageUrl;
	
	/**
	 * @see com.mercadona.library.loans.driven.repositories.models.CustomerMO
	 **/
	public static volatile EntityType<CustomerMO> class_;
	
	/**
	 * @see com.mercadona.library.loans.driven.repositories.models.CustomerMO#publicId
	 **/
	public static volatile SingularAttribute<CustomerMO, Long> publicId;

}

