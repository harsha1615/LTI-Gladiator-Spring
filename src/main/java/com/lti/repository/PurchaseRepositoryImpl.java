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
	
	@Override
	public boolean isUserPurchaseExists(int pid, User user) {
		return (Long) entityManager
				.createQuery("select count(p.id) from Purchase p where p.id=:pid and p.user=:user")
				.setParameter("pid", pid)
				.setParameter("user", user)
				.getSingleResult() == 1;
	}

}
