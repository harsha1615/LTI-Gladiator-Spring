package com.lti.service;

import java.time.LocalDateTime;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lti.entity.Product;
import com.lti.entity.Purchase;
import com.lti.entity.User;
import com.lti.entity.UserPayment;
import com.lti.exception.UserServiceException;
import com.lti.repository.PurchaseRepository;
import com.lti.repository.UserPaymentRepository;
import com.lti.repository.UserRepository;

@Service
@Transactional
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	
	@Autowired
	private UserPaymentRepository userPaymentRepository;
	
	@Override
	public User getUserProfile(int uid) {
		User user;
		if(userRepository.isUserExistsById(uid)) {
			user = userRepository.findById(User.class, uid);
			return user;
		}else {
			throw new UserServiceException("User Does Not Exists");
		}
	}
	
	@Override
	public User payForCard(int uid, float amount) {
		User user;
		if(userRepository.isUserExistsById(uid)) {
			user = userRepository.findById(User.class, uid);
			if(userPaymentRepository.isUserPaid(user)) {
				throw new UserServiceException("User Already Paid");
			}
			UserPayment userPayment = new UserPayment();
			userPayment.setUser(user);
			userPayment.setAmount(amount);
			userPayment.setDateTime(LocalDateTime.now());
			userPayment = userPaymentRepository.save(userPayment);
			user = userRepository.paidForCard(user);
			return user;
		}else {
			throw new UserServiceException("User Does Not Exists");
		}
	}

}
