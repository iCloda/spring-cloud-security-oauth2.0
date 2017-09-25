package com.orieange.repo.extend.druid.msql.support.impl;

import com.orieange.repo.extend.druid.msql.support.IBasicRepository;
import com.orieange.repo.extend.druid.msql.support.PageInfo;
import com.orieange.repo.extend.druid.msql.support.SimplePage;
import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.JpaEntityInformationSupport;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.io.Serializable;
import java.util.*;


public class BasicRepository<T, ID extends Serializable> extends SimpleJpaRepository<T, ID>
		implements IBasicRepository<T, ID> {

	private final EntityManager em;
	private final Class<T> entityClass;
	private final String entityName;

	/**
	 * 构造函数
	 */
	@SuppressWarnings("SpringJavaAutowiringInspection")
	public BasicRepository(JpaEntityInformation<T, ?> entityInformation, EntityManager entityManager) {
		super(entityInformation, entityManager);
		this.em = entityManager;
		this.entityClass = entityInformation.getJavaType();
		this.entityName = entityInformation.getEntityName();
	}

}
