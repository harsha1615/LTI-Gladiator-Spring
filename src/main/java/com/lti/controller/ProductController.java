package com.lti.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lti.dto.ProductDTO;
import com.lti.entity.Product;
import com.lti.service.ProductService;

@RestController
@CrossOrigin
public class ProductController {

	@Autowired
	private ProductService productService;

	@GetMapping(path = "/product")
	public List<ProductDTO> getAllProduct() {
		List<Product> products = this.productService.getAllProducts();
		List<ProductDTO> productsDto = new ArrayList<ProductDTO>();
		for (Product product : products) {
			ProductDTO productDto = new ProductDTO();
			BeanUtils.copyProperties(product, productDto);
//			productDto.setId(product.getId());
//			productDto.setName(product.getName());
//			productDto.setImage(product.getImage());
//			productDto.setDescription(product.getDescription());
//			productDto.setCost(product.getFinalPrice());
			productsDto.add(productDto);
		}
		return productsDto;
	}
	
}
