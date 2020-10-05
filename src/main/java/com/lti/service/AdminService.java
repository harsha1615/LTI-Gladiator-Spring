package com.lti.service;

import java.time.LocalDate;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.lti.entity.Admin;
import com.lti.entity.User;
import com.lti.exception.AdminServiceException;
import com.lti.repository.AdminRepository;
import com.lti.repository.UserRepository;

@Service
@Transactional
public class AdminService {

	@Autowired
	private AdminRepository adminRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	public Admin getProfile(int id) {
		if(adminRepository.isAdminExists(id)) {
			return adminRepository.findById(Admin.class, id);
		}else {
			throw new AdminServiceException("Admin Does Not Exists");
		}
	}
	
	public int login(String username, String password) {
		try {
			int aId = adminRepository.getAdminIdByUsernamePassword(username, password);
			return aId;
		}catch(EmptyResultDataAccessException e) {
			throw new AdminServiceException("Invalid Credentials");			
		}
	}
	
	public List<User> getAllUsers(){
		return userRepository.getAllUsers();
	}
	
	public User activateEmiCard(int uid) {
		if(userRepository.isUserExistsById(uid)) {
			User user = userRepository.getUserProfile(uid);
			user.getEmiCard().setActivated(true);
			user.getEmiCard().setLimit(10000);
			user.getEmiCard().setBalance(10000);
			user.getEmiCard().setValidity(LocalDate.now().plusYears(1));
			return userRepository.save(user);
		}
		throw new AdminServiceException("User Does Not Exists");
	}
	
}
