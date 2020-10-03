package com.lti.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lti.dto.AuthStatusDTO;
import com.lti.dto.UserLoginDTO;
import com.lti.service.AdminService;

@RestController
@CrossOrigin
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	private AdminService adminService;

	@GetMapping("/profile")
	public Object getProfile(@RequestParam("id") int id) {
		try {
			return adminService.getProfile(id);
		}catch(Exception e) {
			AuthStatusDTO status=new AuthStatusDTO();
			status.setSuccess(false);
			status.setMessage(e.getMessage());
			return status;
		}
	}
	
	@PostMapping("/login")
	public AuthStatusDTO login(@RequestBody UserLoginDTO login) {
		try {
			int aId = adminService.login(login.getEmail(), login.getPassword());
			AuthStatusDTO authStatus = new AuthStatusDTO();
			authStatus.setSuccess(true);
			authStatus.setMessage(""+aId);
			return authStatus;	
		}
		catch(Exception e) {
			AuthStatusDTO status=new AuthStatusDTO();
			status.setSuccess(false);
			status.setMessage(e.getMessage());
			return status;
		}
	}
	
}
