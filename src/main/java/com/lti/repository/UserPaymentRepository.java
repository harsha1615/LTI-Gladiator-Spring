package com.lti.repository;

import com.lti.entity.User;

public interface UserPaymentRepository extends GenericRepository {

	public boolean isUserPaid(User user);
	
}