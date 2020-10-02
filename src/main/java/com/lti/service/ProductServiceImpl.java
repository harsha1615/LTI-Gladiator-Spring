package com.lti.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lti.dto.ProductDTO;
import com.lti.entity.Product;
import com.lti.exception.ProductServiceException;
import com.lti.repository.ProductRepository;


@Service
public class ProductServiceImpl implements ProductService {

	
	@Autowired
	ProductRepository productRepository;
	@Transactional
	public List<Product> getAllProduct() {
		return productRepository.getAllProduct();
}

	@Transactional
	public Product readProductById(int id) {
		return productRepository.getProduct(id);
}
	@Override
	public int register(Product product) throws ProductServiceException {
		if(!productRepository.isProductPresent(product.getId())) {
			int id = productRepository.save(product);
			return id;
		}	
	    else 
		    throw new ProductServiceException("Product already registered!");	
	}
}