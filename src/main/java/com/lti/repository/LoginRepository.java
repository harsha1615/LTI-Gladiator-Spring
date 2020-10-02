package com.lti.repository;
import com.lti.entity.User;

public interface LoginRepository {
	
	User findById(int id);
	int findByEmailAndPassword(String email, String password);
	boolean isUserPresent(String email);
}
