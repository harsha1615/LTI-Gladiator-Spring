package com.lti.service;

import java.util.List;
import com.lti.entity.Purchase;
import com.lti.entity.User;

public interface UserService {

	User getProfile(int uid);

	User payForCard(int uid, float amount);
	
	List<Purchase> getAllPurchases(int uId);
	
	Purchase purchaseProduct(int userId,int productId,int emiTenure);
	
	Purchase payEmi(int userId, int purchaseId);
	
}
