package com.lti.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.lti.dto.RegistrationStatusDTO;
import com.lti.entity.User;
import com.lti.exception.RegisterServiceException;
import com.lti.service.RegisterService;

@RestController
@CrossOrigin
public class RegisterController {
	
	@Autowired
	private RegisterService registerService;
	
	@PostMapping(path="/register")
	public RegistrationStatusDTO register(@RequestBody User user) {
	
		String cardNo=""+System.currentTimeMillis();
		user.getEmiCard().setCardNo(cardNo.substring(1,13));
		user.getEmiCard().setValidity(LocalDate.now());
		try {
			boolean success=registerService.register(user);
			RegistrationStatusDTO status=new RegistrationStatusDTO();
			status.setStatus(success);
			status.setStatusMessage("registration successful");
			return status;
		
		}
		catch(RegisterServiceException e){
			RegistrationStatusDTO status=new RegistrationStatusDTO();
			status.setStatus(false);
			status.setStatusMessage(e.getMessage());
			return status;
			
		}
	}

}
