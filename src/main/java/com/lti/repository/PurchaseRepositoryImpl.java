package com.lti.repository;

import java.util.List;

import org.springframework.stereotype.Repository;
import com.lti.entity.Purchase;
import com.lti.entity.User;

@Repository
public class PurchaseRepositoryImpl extends GenericRepositoryImpl implements PurchaseRepository {
	
	@Override
	public List<Purchase> getAllPurchasesOfUser(User user) {
		return entityManager
				.createQuery("select p from Purchase p where p.user=:user", Purchase.class)
				.setParameter("user", user)
				.getResultList();
	}

}
