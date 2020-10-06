package com.lti.repository;

import java.util.List;

import com.lti.entity.Purchase;
import com.lti.entity.User;

public interface PurchaseRepository extends GenericRepository{


	List<Purchase> getAllPurchasesOfUser(User user);
	
	boolean isUserPurchaseExists(int pid, User user);
	

}