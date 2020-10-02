package com.lti.service;

import com.lti.entity.User;

public interface LoginService {
	
	User login(String email, String password);

}
