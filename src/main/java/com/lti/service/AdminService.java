package com.lti.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.lti.entity.Admin;
import com.lti.exception.AdminServiceException;
import com.lti.repository.AdminRepository;

@Service
public class AdminService {

	@Autowired
	private AdminRepository adminRepository;
	
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
	
}
