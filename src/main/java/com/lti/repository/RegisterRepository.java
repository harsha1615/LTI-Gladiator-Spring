package com.lti.repository;



import com.lti.entity.User;

public interface RegisterRepository {

	User save(User user);

	User findById(int id);

	boolean isUserPresent(String email);

}