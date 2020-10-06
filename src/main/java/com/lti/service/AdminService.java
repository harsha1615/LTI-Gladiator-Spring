package com.lti.service;

import java.util.List;

import com.lti.entity.Admin;
import com.lti.entity.Product;
import com.lti.entity.User;

public interface AdminService {

	Admin getProfile(int id);

	int login(String username, String password);

	List<User> getAllUsers();

	User activateEmiCard(int uid);

	Product saveProduct(Product product);

	Product getProduct(int pid);

}