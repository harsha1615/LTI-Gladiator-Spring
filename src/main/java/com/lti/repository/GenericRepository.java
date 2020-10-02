package com.lti.repository;

public interface GenericRepository {

	<T> T save(T object);

	<T> T findById(Class<T> clazz, Object id);

}