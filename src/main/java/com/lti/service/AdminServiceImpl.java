package com.lti.service;

import java.time.LocalDate;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.lti.entity.Admin;
import com.lti.entity.EmiCard;
import com.lti.entity.Product;
import com.lti.entity.User;
import com.lti.exception.AdminServiceException;
import com.lti.repository.AdminRepository;
import com.lti.repository.ProductRepository;
import com.lti.repository.UserRepository;

@Service
@Transactional
public class AdminServiceImpl implements AdminService {

	@Autowired
	private AdminRepository adminRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ProductRepository productRepository;

	@Override
	public Admin getProfile(int id) {
		if (adminRepository.isAdminExists(id)) {
			return adminRepository.findById(Admin.class, id);
		} else {
			throw new AdminServiceException("Admin Does Not Exists");
		}
	}

	@Override
	public int login(String username, String password) {
		try {
			return adminRepository.getAdminIdByUsernamePassword(username, password);
		} catch (EmptyResultDataAccessException e) {
			throw new AdminServiceException("Invalid Credentials");
		}
	}

	@Override
	public List<User> getAllUsers() {
		return userRepository.getAllUsers();
	}

	@Override
	public User activateEmiCard(int uid) {
		if (userRepository.isUserExistsById(uid)) {
			User user = userRepository.getUserProfile(uid);
			if(user.isPaidForCard()) {
				if(user.getEmiCard().getCardType() == EmiCard.CardType.GOLD) {
					user.getEmiCard().setLimit(50000);
					user.getEmiCard().setBalance(50000);				
				}
				if(user.getEmiCard().getCardType() == EmiCard.CardType.TITANIUM) {
					user.getEmiCard().setLimit(100000);
					user.getEmiCard().setBalance(100000);				
				}
				user.getEmiCard().setActivated(true);
				user.getEmiCard().setValidity(LocalDate.now().plusYears(1));
				return userRepository.save(user);				
			}
			throw new AdminServiceException("User Not Paid for the Card");
		}
		throw new AdminServiceException("User Does Not Exists");
	}

	@Override
	public Product saveProduct(Product product) {
		return productRepository.save(product);
	}
	
	@Override
	public Product getProduct(int pid) {
		if(productRepository.isProductExists(pid)) {
			return productRepository.findById(Product.class, pid);
		}
		throw new AdminServiceException("Product with ID:"+pid+" does not exists");
	}

}
