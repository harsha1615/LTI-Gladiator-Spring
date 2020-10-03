package com.lti.repository;

import java.util.List;

import com.lti.entity.Product;

public interface ProductRepository extends GenericRepository {

	boolean isProductExists(int id);

	List<Product> getAllProducts(); 
	
}
