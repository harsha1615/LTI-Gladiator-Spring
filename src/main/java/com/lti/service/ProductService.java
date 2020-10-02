package com.lti.service;

import java.util.List;

import com.lti.dto.ProductDTO;
import com.lti.entity.Product;
import com.lti.exception.ProductServiceException;



public interface ProductService {

	public List<Product> getAllProduct();
	public Product readProductById(int id);
	int register(Product product) throws ProductServiceException;
	//To be added-save product
	
}
