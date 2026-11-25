package com.mercadona.library.loans.driven.repositories.models;

import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import java.util.UUID;

@StaticMetamodel(BookMO.class)
@Generated("org.hibernate.processor.HibernateProcessor")
public abstract class BookMO_ {

	public static final String ID = "id";
	public static final String PUBLIC_ID = "publicId";

	
	/**
	 * @see com.mercadona.library.loans.driven.repositories.models.BookMO#id
	 **/
	public static volatile SingularAttribute<BookMO, UUID> id;
	
	/**
	 * @see com.mercadona.library.loans.driven.repositories.models.BookMO
	 **/
	public static volatile EntityType<BookMO> class_;
	
	/**
	 * @see com.mercadona.library.loans.driven.repositories.models.BookMO#publicId
	 **/
	public static volatile SingularAttribute<BookMO, Long> publicId;

}

