package com.lti.repository;

import org.springframework.stereotype.Repository;

import com.lti.entity.User;

@Repository
public class UserPaymentRepositoryImpl extends GenericRepositoryImpl implements UserPaymentRepository {

	@Override
	public boolean isUserPaid(User user) {
		return (Long) entityManager
				.createQuery("select count(up.user) from UserPayment up where up.user=:user")
				.setParameter("user", user)
				.getSingleResult() == 1;
	}
	
}
