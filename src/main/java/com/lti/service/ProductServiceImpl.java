package com.lti.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lti.entity.Product;
import com.lti.exception.ProductServiceException;
import com.lti.repository.ProductRepository;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepository;

	@Override
	public List<Product> getAllProducts() {
		return productRepository.getAllProducts();
	}

	@Override
	public Product getProductById(int id) {
		if (productRepository.isProductExists(id)) {
			return productRepository.findById(Product.class, id);
		}
		throw new ProductServiceException("Product Does Not Exists");
	}
	
}
