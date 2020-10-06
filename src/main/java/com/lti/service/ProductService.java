package com.lti.service;

import java.util.List;
import com.lti.entity.Product;

public interface ProductService {

	List<Product> getAllProducts();
	
	Product getProductById(int id);
	
}
