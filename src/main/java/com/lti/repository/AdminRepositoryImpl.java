package com.lti.repository;

import org.springframework.stereotype.Repository;

@Repository
public class AdminRepositoryImpl extends GenericRepositoryImpl implements AdminRepository {

	@Override
	public boolean isAdminExists(int id) {
		return (Long) entityManager
				.createQuery("select count(a) from Admin a where a.id=:aid")
				.setParameter("aid", id)
				.getSingleResult() == 1;
	}
	
	@Override
	public int getAdminIdByUsernamePassword(String uname, String password) {
		return entityManager
				.createQuery("select a.id from Admin a where a.username=:uname and a.password=:password",Integer.class)
				.setParameter("uname", uname)
				.setParameter("password", password)
				.getSingleResult();
	}
	
}
