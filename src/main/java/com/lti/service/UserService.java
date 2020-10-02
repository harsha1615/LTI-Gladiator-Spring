package com.lti.service;

import com.lti.entity.User;

public interface UserService {

	User getUserProfile(int uid);

	User payForCard(int uid, float amount);

}