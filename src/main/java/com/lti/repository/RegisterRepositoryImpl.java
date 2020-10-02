package com.lti.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import com.lti.entity.User;
@Repository
public class RegisterRepositoryImpl implements RegisterRepository{
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public User save(User user) {
	return entityManager.merge(user);
	}
	
	@Override
	public User findById(int id) {
		return entityManager.find(User.class, id);
	}
	
	@Override
	public boolean isUserPresent(String email) {
		return (Long) entityManager
				.createQuery("select count(u.id) from User u where u.email= :em")
				.setParameter("em", email)
				.getSingleResult() == 1 ? true : false;
	}
	
}
