package com.lti.repository;

import java.util.List;

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
	public int getUserIdByEmailPassword(String email, String password) {
		return entityManager
				.createQuery("select u.id from User u where u.email=:email and u.password=:password", Integer.class)
				.setParameter("email",email)
				.setParameter("password", password)
				.getSingleResult();
	}
	
	@Override
	public List<User> getAllUsers(){
		return entityManager
				.createQuery("select u from User u", User.class)
				.getResultList();
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
	
	public boolean isUserExistsByUsername(String uname) {
		return (Long) entityManager
				.createQuery("select count(u.id) from User u where u.username=:uname")
				.setParameter("uname", uname)
				.getSingleResult() == 1;
	}
	
}
