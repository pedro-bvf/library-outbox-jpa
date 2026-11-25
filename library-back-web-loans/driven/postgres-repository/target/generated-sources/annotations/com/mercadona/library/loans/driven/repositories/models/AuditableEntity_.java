package com.mercadona.library.loans.driven.repositories.models;

import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.MappedSuperclassType;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import java.time.OffsetDateTime;

@StaticMetamodel(AuditableEntity.class)
@Generated("org.hibernate.processor.HibernateProcessor")
public abstract class AuditableEntity_ {

	public static final String CREATED_DATE = "createdDate";
	public static final String CREATED_BY = "createdBy";
	public static final String MODIFIED_DATE = "modifiedDate";
	public static final String MODIFIED_BY = "modifiedBy";

	
	/**
	 * @see com.mercadona.library.loans.driven.repositories.models.AuditableEntity#createdDate
	 **/
	public static volatile SingularAttribute<AuditableEntity, OffsetDateTime> createdDate;
	
	/**
	 * @see com.mercadona.library.loans.driven.repositories.models.AuditableEntity#createdBy
	 **/
	public static volatile SingularAttribute<AuditableEntity, String> createdBy;
	
	/**
	 * @see com.mercadona.library.loans.driven.repositories.models.AuditableEntity#modifiedDate
	 **/
	public static volatile SingularAttribute<AuditableEntity, OffsetDateTime> modifiedDate;
	
	/**
	 * @see com.mercadona.library.loans.driven.repositories.models.AuditableEntity#modifiedBy
	 **/
	public static volatile SingularAttribute<AuditableEntity, String> modifiedBy;
	
	/**
	 * @see com.mercadona.library.loans.driven.repositories.models.AuditableEntity
	 **/
	public static volatile MappedSuperclassType<AuditableEntity> class_;

}

