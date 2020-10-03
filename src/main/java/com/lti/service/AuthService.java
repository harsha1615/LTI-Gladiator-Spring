package com.lti.service;

import com.lti.entity.User;

public interface AuthService {

	boolean register(User user);

	int login(String email, String password);

}