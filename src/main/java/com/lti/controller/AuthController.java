package com.lti.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.lti.entity.User;
import com.lti.dto.LoginDTO;
import com.lti.dto.StatusDTO;
import com.lti.service.AuthService;

@RestController
@CrossOrigin
public class AuthController {
	
	@Autowired
	private AuthService authService;
	
	@PostMapping(path="/register")
	public StatusDTO register(@RequestBody User user) {
		String cardNo=""+System.currentTimeMillis();
		user.getEmiCard().setCardNo(cardNo.substring(1,13));
		user.getEmiCard().setValidity(LocalDate.now());
		try {
			boolean success=authService.register(user);
			StatusDTO status=new StatusDTO();
			status.setSuccess(success);
			status.setMessage("Registration Successful");
			return status;
		}
		catch(Exception e){
			StatusDTO status=new StatusDTO();
			status.setSuccess(false);
			status.setMessage(e.getMessage());
			return status;
		}
	}
	
	@PostMapping(path="/login") 
	public StatusDTO login(@RequestBody LoginDTO login) {
		try {
			int uid = authService.login(login.getEmail(), login.getPassword());
			StatusDTO authStatus = new StatusDTO();
			authStatus.setSuccess(true);
			authStatus.setMessage(""+uid);
			return authStatus;	
		}
		catch(Exception e) {
			StatusDTO status=new StatusDTO();
			status.setSuccess(false);
			status.setMessage(e.getMessage());
			return status;
		}
	}

}
