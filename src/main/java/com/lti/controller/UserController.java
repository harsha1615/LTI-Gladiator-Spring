package com.lti.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lti.dto.ProductDTO;
import com.lti.dto.UserEmiCardDTO;
import com.lti.dto.UserErrorDTO;
import com.lti.dto.UserPayDTO;
import com.lti.dto.UserProfileDTO;
import com.lti.entity.EmiCard;
import com.lti.entity.User;
import com.lti.service.UserService;

@RestController
@RequestMapping(path = "/user")
@CrossOrigin
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/profile")
	public Object getProfile(@RequestParam("id") int uid) {
		try {
			User user = userService.getUserProfile(uid);
			return convertToDTO(user);
		}catch(Exception e) {
			UserErrorDTO error = new UserErrorDTO();
			error.setErrorCode(403);
			error.setErrorMsg(e.getMessage());
			return error;
		}
	}
	
	@PostMapping("/pay-for-card")
	public Object payForCard(@RequestBody UserPayDTO userpay) {
		try {
			if(!userpay.isPay()) {
				throw new Exception("Invalid Request");
			}
			User user = userService.payForCard(userpay.getUid(), 1000);
			return convertToDTO(user);
		}catch(Exception e) {
			UserErrorDTO error = new UserErrorDTO();
			error.setErrorCode(403);
			error.setErrorMsg(e.getMessage());
			return error;
		}
	}
	
	
	private UserProfileDTO convertToDTO(User user) {
		UserProfileDTO userdto = new UserProfileDTO();
		userdto.setId(user.getId());
		userdto.setName(user.getName());
		userdto.setUsername(user.getUsername());
		userdto.setEmail(user.getEmail());
		userdto.setPhone(user.getPhoneNo());
		userdto.setAddress(user.getAddress());
		userdto.setPaidForCard(user.isPaidForCard());
		
		UserEmiCardDTO useremidto = new UserEmiCardDTO();
		EmiCard useremi = user.getEmiCard();
		useremidto.setId(useremi.getId());
		useremidto.setCardNo(useremi.getCardNo());
		useremidto.setCardType(useremi.getCardType().toString());
		useremidto.setValidity(useremi.getValidity().toString());
		useremidto.setActivated(useremi.isActivated());
		useremidto.setBalance(useremi.getBalance());
		useremidto.setLimit(useremi.getLimit());
		
		userdto.setEmiCard(useremidto);
		
		return userdto;
	}
	
	
}
