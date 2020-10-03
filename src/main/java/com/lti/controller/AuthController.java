package com.lti.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.lti.entity.User;
import com.lti.dto.UserLoginDTO;
import com.lti.dto.AuthStatusDTO;
import com.lti.service.AuthService;

@RestController
@CrossOrigin
public class AuthController {
	
	@Autowired
	private AuthService authService;
	
	@PostMapping(path="/register")
	public AuthStatusDTO register(@RequestBody User user) {
	
		String cardNo=""+System.currentTimeMillis();
		user.getEmiCard().setCardNo(cardNo.substring(1,13));
		user.getEmiCard().setValidity(LocalDate.now());
		try {
			boolean success=authService.register(user);
			AuthStatusDTO status=new AuthStatusDTO();
			status.setSuccess(success);
			status.setMessage("Registration Successful");
			return status;
		}
		catch(Exception e){
			AuthStatusDTO status=new AuthStatusDTO();
			status.setSuccess(false);
			status.setMessage(e.getMessage());
			return status;
		}
	}
	
	@PostMapping(path="/login") 
	public AuthStatusDTO login(@RequestBody UserLoginDTO login) {
		try {
			int uid = authService.login(login.getEmail(), login.getPassword());
			AuthStatusDTO authStatus = new AuthStatusDTO();
			authStatus.setSuccess(true);
			authStatus.setMessage(""+uid);
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
