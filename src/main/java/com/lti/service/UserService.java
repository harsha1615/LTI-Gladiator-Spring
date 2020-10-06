package com.lti.service;

import java.util.List;


import com.lti.entity.Purchase;
import com.lti.entity.User;

public interface UserService {

	User getUserProfile(int uid);

	User payForCard(int uid, float amount);
	
	

}