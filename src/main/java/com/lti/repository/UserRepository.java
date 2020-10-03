package com.lti.repository;

import java.util.List;

import com.lti.entity.User;

public interface UserRepository extends GenericRepository {

	User paidForCard(User user);

	User getUserProfile(int id);
	
	int getUserIdByEmailPassword(String email, String password);
	
	List<User> getAllUsers();

	boolean isUserExistsById(int id);

	boolean isUserExistsByEmail(String email);
	
	boolean isUserExistsByUsername(String uname);

}