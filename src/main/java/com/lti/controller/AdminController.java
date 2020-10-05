package com.lti.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lti.dto.ActivateCardDTO;
import com.lti.dto.AuthStatusDTO;
import com.lti.dto.UserEmiCardDTO;
import com.lti.dto.UserLoginDTO;
import com.lti.dto.UserProfileDTO;
import com.lti.entity.EmiCard;
import com.lti.entity.User;
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
	
	@GetMapping("/users")
	public List<UserProfileDTO> getAllUsers(){
		List<User> users = adminService.getAllUsers();
		List<UserProfileDTO> userProfiles = new ArrayList<UserProfileDTO>();
		for(User user : users) {
			UserProfileDTO userProfile = convertToDTO(user);
			userProfiles.add(userProfile);
		}
		return userProfiles;
	}
	
	@PostMapping("/users/activate-usercard")
	public Object activateUserCard(@RequestBody ActivateCardDTO req) {
		try {
			if(req.isActivateCard()) {
				User user = adminService.activateEmiCard(req.getUid());
				return convertToDTO(user);
			}
			throw new Exception("Invalid Request");
		}catch(Exception e) {
			AuthStatusDTO status=new AuthStatusDTO();
			status.setSuccess(false);
			status.setMessage(e.getMessage());
			return status;
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
