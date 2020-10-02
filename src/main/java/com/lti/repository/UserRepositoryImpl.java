package com.lti.repository;

import org.springframework.stereotype.Repository;

import com.lti.entity.User;

@Repository
public class UserRepositoryImpl extends GenericRepositoryImpl implements UserRepository  {
	
	@Override
	public User paidForCard(User user) {
		user.setPaidForCard(true);
		return save(user);
	}
	
	@Override
	public User getUserProfile(int id) {
		return findById(User.class, id);
	}
	
	@Override
	public boolean isUserExistsById(int id) {
		return (Long) entityManager
				.createQuery("select count(u.id) from User u where u.id=:id")
				.setParameter("id", id)
				.getSingleResult() == 1;
	}
	
	@Override
	public boolean isUserExistsByEmail(String email) {
		return (Long) entityManager
				.createQuery("select count(u.id) from User u where u.email=:email")
				.setParameter("email", email)
				.getSingleResult() == 1;
	}
	
}
