package com.lti.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.lti.entity.Product;

@Repository
public class ProductRepositoryImpl extends GenericRepositoryImpl implements ProductRepository {
	
	@Override
	public List<Product> getAllProducts() {
		return entityManager
				.createQuery("select p from Product p", Product.class)
				.getResultList();
	}

	@Override
	public boolean isProductExists(int id) {
		return (Long) entityManager
				.createQuery("select count(p.id) from Product p where p.id=:id")
				.setParameter("id", id)
				.getSingleResult() == 1;
	}

}
