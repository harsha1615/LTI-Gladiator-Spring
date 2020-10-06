package com.lti.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lti.dto.AuthStatusDTO;
import com.lti.dto.EmiPaymentDTO;
import com.lti.dto.PayEmiDTO;
import com.lti.dto.ProductDTO;
import com.lti.dto.PurchaseDTO;
import com.lti.dto.PurchaseProductDTO;
import com.lti.entity.EmiPayment;
import com.lti.entity.Purchase;
import com.lti.service.PurchaseService;

@RestController
@CrossOrigin
public class PurchaseController {
	
	@Autowired
	private PurchaseService purchaseService;

	@GetMapping("/purchases")
	public List<PurchaseDTO> getAllPurchasesOfUser(@RequestParam("uid") int uid) {
		List<Purchase> purchases = this.purchaseService.getAllPurchasesOfUser(uid);
		List<PurchaseDTO> purchasesDto = new ArrayList<>();
		for (Purchase purchase : purchases) {
			PurchaseDTO purchaseDto = convertToDTO(purchase);
			purchasesDto.add(purchaseDto);
		}
		return purchasesDto;
	}
	
	@PostMapping(path="/purchases")
	public Object purchaseProduct(@RequestBody PurchaseProductDTO purchaseProduct) {
		try {
			purchaseService.purchaseProduct(purchaseProduct.getUserId(), purchaseProduct.getProductId(), purchaseProduct.getEmiTenure());
			AuthStatusDTO status=new AuthStatusDTO();
			status.setSuccess(true);
			status.setMessage("Product Purchased Successfully");
			return status;
		}catch(Exception e){
			AuthStatusDTO status=new AuthStatusDTO();
			status.setSuccess(false);
			status.setMessage(e.getMessage());
			return status;
		}
	}
	
//	@PostMapping("/pay-emi")
//	publi Object payEmi(@RequestBody PayEmiDTO payEmiDto) {
//		
//	}
	
	private PurchaseDTO convertToDTO(Purchase purchase) {
		PurchaseDTO purchaseDto = new PurchaseDTO();
		BeanUtils.copyProperties(purchase, purchaseDto);
		ProductDTO productDto = new ProductDTO();
		BeanUtils.copyProperties(purchase.getProduct(), productDto);
		purchaseDto.setProduct(productDto);
		List<EmiPaymentDTO> emiPaymentsDTO = new ArrayList<>();
		for(EmiPayment emiPayment : purchase.getEmiPayments()) {
			EmiPaymentDTO emiPaymentDTO = new EmiPaymentDTO();
			BeanUtils.copyProperties(emiPayment, emiPaymentDTO);
			emiPaymentsDTO.add(emiPaymentDTO);
		}
		purchaseDto.setEmiPayments(emiPaymentsDTO);
		return purchaseDto;
	}

}
