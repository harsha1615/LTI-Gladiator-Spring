package com.lti.utils;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.lti.dto.EmiPaymentDTO;
import com.lti.dto.ProductDTO;
import com.lti.dto.PurchaseDTO;
import com.lti.dto.UserEmiCardDTO;
import com.lti.dto.UserProfileDTO;
import com.lti.entity.EmiPayment;
import com.lti.entity.Purchase;
import com.lti.entity.User;

@Component
public class AppUtils {
	
	public UserProfileDTO convertToDTO(User user) {
		UserProfileDTO userdto = new UserProfileDTO();
		BeanUtils.copyProperties(user, userdto);
		UserEmiCardDTO useremi = new UserEmiCardDTO();
		BeanUtils.copyProperties(user.getEmiCard(), useremi);
		userdto.setEmiCard(useremi);
		return userdto;
	}
	
	public PurchaseDTO convertToDTO(Purchase purchase) {
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
