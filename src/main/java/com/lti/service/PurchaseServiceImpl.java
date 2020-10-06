package com.lti.service;

import java.time.LocalDateTime;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lti.entity.EmiPayment;
import com.lti.entity.Product;
import com.lti.entity.Purchase;
import com.lti.entity.User;
import com.lti.exception.UserServiceException;
import com.lti.repository.ProductRepository;
import com.lti.repository.PurchaseRepository;
import com.lti.repository.UserRepository;

@Service
@Transactional
public class PurchaseServiceImpl implements PurchaseService {
	
	@Autowired
	private PurchaseRepository purchaseRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
	@Override
	public List<Purchase> getAllPurchasesOfUser(int uId) {
		if(userRepository.isUserExistsById(uId)) {
			User user = userRepository.findById(User.class, uId);
			return purchaseRepository.getAllPurchasesOfUser(user);
		}
		throw new UserServiceException("User Not Found");
	}
	
	@Override
	public Purchase purchaseProduct(int userId,int productId,int emiTenure) {
		if(userRepository.isUserExistsById(userId)) {
			User user = userRepository.findById(User.class, userId);
			if(productRepository.isProductExists(productId)) {
				Product product = productRepository.findById(Product.class, productId);
				Purchase purchase = new Purchase();
				purchase.setUser(user);
				purchase.setProduct(product);
				purchase.setEmiTenure(emiTenure);
				purchase.setPrice(product.getCost());
				float emiAmount = Math.round(100*product.getCost()/emiTenure)/100;
				purchase.setEmiAmount(emiAmount);
				purchase.setDateTime(LocalDateTime.now());
				return purchaseRepository.save(purchase);
			}
			throw new UserServiceException("Product with ID:"+productId+" does not exists");
		}
		throw new UserServiceException("User with ID:"+userId+" does not exists");
	}
	
	@Override
	public Purchase payEmi(int userId, int purchaseId) {
		if(userRepository.isUserExistsById(userId)) {
			User user = userRepository.findById(User.class, userId);
			if(purchaseRepository.isUserPurchaseExists(purchaseId, user)) {
				Purchase purchase = purchaseRepository.findById(Purchase.class, purchaseId);
				if(user.getEmiCard().getBalance() >= purchase.getEmiAmount()) {
					EmiPayment emiPayment = new EmiPayment();
					emiPayment.setPurchase(purchase);
					emiPayment.setEmiAmount(purchase.getEmiAmount());
					emiPayment.setEmiNo(purchase.getEmisPaid()+1);
					float lateFee = 0;
					emiPayment.setLateFee(lateFee);
					emiPayment.setDateTime(LocalDateTime.now());
					emiPayment.setTotalAmount(emiPayment.getEmiAmount()+emiPayment.getLateFee());
					List<EmiPayment> emiPayments = purchase.getEmiPayments();
					emiPayments.add(emiPayment);
					purchase.setEmiPayments(emiPayments);
					purchase.setEmisPaid(purchase.getEmisPaid()+1);
					user.getEmiCard().setBalance(user.getEmiCard().getBalance()-emiPayment.getTotalAmount());
					userRepository.save(user);
					return purchaseRepository.save(purchase);
				}else {
					throw new UserServiceException("Low Balance in your Card");
				}
			}
			throw new UserServiceException("Purchase with ID:"+purchaseId+" does not exists");
		}
		throw new UserServiceException("User with ID:"+userId+" does not exists");
	}
	
}
