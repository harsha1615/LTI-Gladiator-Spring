package com.lti.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.lti.entity.User;
import com.lti.exception.AuthServiceException;
import com.lti.repository.UserRepository;

@Service
@Transactional
public class AuthServiceImpl implements AuthService {

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public boolean register(User user) {
		if(userRepository.isUserExistsByEmail(user.getEmail())) {
			throw new AuthServiceException("Email Already Exists");
		}
		if(userRepository.isUserExistsByUsername(user.getUsername())) {
			throw new AuthServiceException("Username Already Exists");
		}
		userRepository.save(user);
		return true;
	}

	@Override
	public int login(String email, String password) {
		if (!userRepository.isUserExistsByEmail(email)) {
			throw new AuthServiceException("User Not Registered");
		}
		try {
			int uid = userRepository.getUserIdByEmailPassword(email, password);
			return uid;
		}catch(EmptyResultDataAccessException e) {
			throw new AuthServiceException("Invalid email or password");			
		}
	}
	
}
