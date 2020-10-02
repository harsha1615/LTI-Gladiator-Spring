package com.lti.repository;

import java.util.List;

import com.lti.entity.Product;

public interface ProductRepository {

	boolean isProductPresent(int id);
	public List<Product> getAllProduct(); 
	Product getProduct(int id);
	int save(Product product);
	
}