package com.lti.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lti.entity.User;
import com.lti.exception.RegisterServiceException;

import com.lti.repository.RegisterRepository;


@Service
@Transactional
public class RegisterServiceImpl implements RegisterService {
	
	@Autowired
	private RegisterRepository registerRepository;
	
	@Override
	public boolean register(User user) {
		if(!registerRepository.isUserPresent(user.getEmail())) {
			registerRepository.save(user);
			return true;
		}
		else 
			throw new RegisterServiceException("Customer already registered");
	}

}
