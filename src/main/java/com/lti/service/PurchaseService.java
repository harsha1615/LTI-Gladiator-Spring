package com.lti.service;

import java.util.List;

import com.lti.entity.Purchase;

public interface PurchaseService {

	List<Purchase> getAllPurchasesOfUser(int uId);
	
	Purchase purchaseProduct(int userId,int productId,int emiTenure);
	
	Purchase payEmi(int userId, int purchaseId);

}