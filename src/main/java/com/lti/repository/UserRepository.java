package com.lti.repository;

import com.lti.entity.User;

public interface UserRepository extends GenericRepository {

	User paidForCard(User user);

	User getUserProfile(int id);

	boolean isUserExistsById(int id);

	boolean isUserExistsByEmail(String email);

}