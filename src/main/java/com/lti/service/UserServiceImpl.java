package com.lti.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lti.entity.EmiPayment;
import com.lti.entity.Product;
import com.lti.entity.Purchase;
import com.lti.entity.User;
import com.lti.entity.UserPayment;
import com.lti.exception.UserServiceException;
import com.lti.repository.ProductRepository;
import com.lti.repository.PurchaseRepository;
import com.lti.repository.UserPaymentRepository;
import com.lti.repository.UserRepository;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserPaymentRepository userPaymentRepository;

	@Autowired
	private PurchaseRepository purchaseRepository;

	@Autowired
	private ProductRepository productRepository;

	@Override
	public User getProfile(int uid) {
		if (userRepository.isUserExistsById(uid)) {
			return userRepository.findById(User.class, uid);
		} else {
			throw new UserServiceException("User Does Not Exists");
		}
	}

	@Override
	public User payForCard(int uid, float amount) {
		if (userRepository.isUserExistsById(uid)) {
			User user = userRepository.findById(User.class, uid);
			if (userPaymentRepository.isUserPaid(user)) {
				throw new UserServiceException("User Already Paid");
			}
			UserPayment userPayment = new UserPayment();
			userPayment.setUser(user);
			userPayment.setAmount(amount);
			userPayment.setDateTime(LocalDateTime.now());
			userPayment = userPaymentRepository.save(userPayment);
			user = userRepository.paidForCard(user);
			return user;
		} else {
			throw new UserServiceException("User Does Not Exists");
		}
	}

	@Override
	public List<Purchase> getAllPurchases(int uId) {
		if (userRepository.isUserExistsById(uId)) {
			User user = userRepository.findById(User.class, uId);
			return purchaseRepository.getAllPurchasesOfUser(user);
		}
		throw new UserServiceException("User Not Found");
	}

	@Override
	public Purchase purchaseProduct(int userId, int productId, int emiTenure) {
		if (userRepository.isUserExistsById(userId)) {
			User user = userRepository.findById(User.class, userId);
			if (productRepository.isProductExists(productId)) {
				if (user.getEmiCard().isActivated()) {
					Product product = productRepository.findById(Product.class, productId);
					Purchase purchase = new Purchase();
					if (product.getCost() <= user.getEmiCard().getBalance()) {
						purchase.setUser(user);
						purchase.setProduct(product);
						purchase.setEmiTenure(emiTenure);
						purchase.setPrice(product.getCost());
						float emiAmount = Math.round(100 * product.getCost() / emiTenure) / 100;
						purchase.setEmiAmount(emiAmount);
						purchase.setDateTime(LocalDateTime.now());
						purchase.setEmiPayments(new ArrayList<>());
						return purchaseRepository.save(purchase);
					}
					throw new UserServiceException("Low Balance in your card");
				}
				throw new UserServiceException("Card not activated");
			}
			throw new UserServiceException("Product with ID:" + productId + " does not exists");
		}
		throw new UserServiceException("User with ID:" + userId + " does not exists");
	}

	@Override
	public Purchase payEmi(int userId, int purchaseId) {
		if (userRepository.isUserExistsById(userId)) {
			User user = userRepository.findById(User.class, userId);
			if (purchaseRepository.isUserPurchaseExists(purchaseId, user)) {
				Purchase purchase = purchaseRepository.findById(Purchase.class, purchaseId);
				if(purchase.getEmisPaid() == purchase.getEmiTenure()) {
					throw new UserServiceException("All EMIs Paid");
				}
				float lateFee = 0;
//				long days = Duration.between(LocalDate.now(), purchase.getDateTime().toLocalDate().plusMonths(purchase.getEmisPaid())).toDays();
//				if(days > 30) {
//					lateFee = (days-30)*10;
//				}
				EmiPayment emiPayment = new EmiPayment();
				emiPayment.setEmiAmount(purchase.getEmiAmount());
				emiPayment.setLateFee(lateFee);
				emiPayment.setTotalAmount(emiPayment.getEmiAmount() + emiPayment.getLateFee());
				if(user.getEmiCard().getBalance() >= emiPayment.getTotalAmount()) {
					emiPayment.setPurchase(purchase);
					emiPayment.setEmiNo(purchase.getEmisPaid() + 1);
					emiPayment.setDateTime(LocalDateTime.now());
					List<EmiPayment> emiPayments = purchase.getEmiPayments();
					emiPayments.add(emiPayment);
					purchase.setEmiPayments(emiPayments);
					purchase.setEmisPaid(purchase.getEmisPaid() + 1);
					user.getEmiCard().setBalance(user.getEmiCard().getBalance() - emiPayment.getTotalAmount());
					userRepository.save(user);
					return purchaseRepository.save(purchase);					
				}
				throw new UserServiceException("Low Balance in your Card");
			}
			throw new UserServiceException("Purchase with ID:" + purchaseId + " does not exists");
		}
		throw new UserServiceException("User with ID:" + userId + " does not exists");
	}

}
