package com.lti.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

@Repository
public class GenericRepositoryImpl implements GenericRepository {

	@PersistenceContext
	protected EntityManager entityManager;
	
	@Override
	public <T> T save(T object) {
		return entityManager.merge(object);
	}
	
	@Override
	public <T> T findById(Class<T> clazz, Object id) {
		return entityManager.find(clazz, id);
	}
	
}
