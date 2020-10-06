package com.lti.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lti.dto.ActivateCardDTO;
import com.lti.dto.AdminProductDTO;
import com.lti.dto.StatusDTO;
import com.lti.dto.LoginDTO;
import com.lti.dto.UserProfileDTO;
import com.lti.entity.Product;
import com.lti.entity.User;
import com.lti.service.AdminService;
import com.lti.utils.AppUtils;

@RestController
@CrossOrigin
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	private AdminService adminService;
	
	@Autowired
	private AppUtils appUtils;

	@GetMapping("/profile")
	public Object getProfile(@RequestParam("id") int id) {
		try {
			return adminService.getProfile(id);
		}catch(Exception e) {
			StatusDTO status=new StatusDTO();
			status.setSuccess(false);
			status.setMessage(e.getMessage());
			return status;
		}
	}
	
	@PostMapping("/login")
	public StatusDTO login(@RequestBody LoginDTO login) {
		try {
			int aId = adminService.login(login.getEmail(), login.getPassword());
			StatusDTO authStatus = new StatusDTO();
			authStatus.setSuccess(true);
			authStatus.setMessage(""+aId);
			return authStatus;	
		}
		catch(Exception e) {
			StatusDTO status=new StatusDTO();
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
			UserProfileDTO userProfile = appUtils.convertToDTO(user);
			userProfiles.add(userProfile);
		}
		return userProfiles;
	}
	
	@PostMapping("/users/activate-usercard")
	public Object activateUserCard(@RequestBody ActivateCardDTO req) {
		try {
			if(req.isActivateCard()) {
				User user = adminService.activateEmiCard(req.getUid());
				return appUtils.convertToDTO(user);
			}
			throw new Exception("Invalid Request");
		}catch(Exception e) {
			StatusDTO status=new StatusDTO();
			status.setSuccess(false);
			status.setMessage(e.getMessage());
			return status;
		}
	}
	
	@GetMapping("/products")
	public Object getProduct(@RequestParam("pid") int pid) {
		try {
			Product product = adminService.getProduct(pid);
			AdminProductDTO adminProduct = new AdminProductDTO();
			BeanUtils.copyProperties(product, adminProduct);
			return adminProduct;
		}catch(Exception e) {
			StatusDTO status=new StatusDTO();
			status.setSuccess(false);
			status.setMessage(e.getMessage());
			return status;
		}
	}
	
	@PostMapping("/products")
	public Object addProduct(@RequestBody Product product) {
		try {
			adminService.saveProduct(product);
			StatusDTO status=new StatusDTO();
			status.setSuccess(true);
			status.setMessage("Product added Successfully");
			return status;
		}catch(Exception e) {
			StatusDTO status=new StatusDTO();
			status.setSuccess(false);
			status.setMessage(e.getMessage());
			return status;
		}
	}
	
}
