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

import com.lti.dto.StatusDTO;
import com.lti.dto.PayEmiDTO;
import com.lti.dto.PurchaseDTO;
import com.lti.dto.PurchaseProductDTO;
import com.lti.dto.UserPayDTO;
import com.lti.entity.Purchase;
import com.lti.entity.User;
import com.lti.service.UserService;
import com.lti.utils.AppUtils;

@RestController
@RequestMapping(path = "/user")
@CrossOrigin
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private AppUtils appUtils;
	
	@GetMapping("/profile")
	public Object getProfile(@RequestParam("id") int uid) {
		try {
			User user = userService.getProfile(uid);
			return appUtils.convertToDTO(user);
		}catch(Exception e) {
			StatusDTO status=new StatusDTO();
			status.setSuccess(false);
			status.setMessage(e.getMessage());
			return status;
		}
	}
	
	@PostMapping("/pay-for-card")
	public Object payForCard(@RequestBody UserPayDTO userpay) {
		try {
			if(!userpay.isPay()) {
				throw new Exception("Invalid Request");
			}
			User user = userService.payForCard(userpay.getUid(), 100);
			return appUtils.convertToDTO(user);
		}catch(Exception e) {
			StatusDTO status=new StatusDTO();
			status.setSuccess(false);
			status.setMessage(e.getMessage());
			return status;
		}
	}

	@GetMapping("/purchases")
	public List<PurchaseDTO> getAllPurchasesOfUser(@RequestParam("uid") int uid) {
		List<Purchase> purchases = this.userService.getAllPurchases(uid);
		List<PurchaseDTO> purchasesDto = new ArrayList<>();
		for (Purchase purchase : purchases) {
			PurchaseDTO purchaseDto = appUtils.convertToDTO(purchase);
			purchasesDto.add(purchaseDto);
		}
		return purchasesDto;
	}
	
	@PostMapping(path="/purchases")
	public Object purchaseProduct(@RequestBody PurchaseProductDTO purchaseProduct) {
		try {
			Purchase purchase = userService.purchaseProduct(purchaseProduct.getUserId(), purchaseProduct.getProductId(), purchaseProduct.getEmiTenure());
			return appUtils.convertToDTO(purchase);
		}catch(Exception e){
			e.printStackTrace();
			StatusDTO status=new StatusDTO();
			status.setSuccess(false);
			status.setMessage(e.getMessage());
			return status;
		}
	}
	
	@PostMapping("/pay-emi")
	public Object payEmi(@RequestBody PayEmiDTO payEmiDto) {
		try {
			Purchase purchase = userService.payEmi(payEmiDto.getUserId(), payEmiDto.getPurchaseId());
			PurchaseDTO purchaseDto = appUtils.convertToDTO(purchase);
			return purchaseDto;
		}catch(Exception e){
			e.printStackTrace();
			StatusDTO status=new StatusDTO();
			status.setSuccess(false);
			status.setMessage(e.getMessage());
			return status;
		}
	}
	
}
