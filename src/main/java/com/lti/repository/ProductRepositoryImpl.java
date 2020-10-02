package com.lti.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.stereotype.Component;

import com.lti.entity.Product;

@Component("Products")
public class ProductRepositoryImpl implements ProductRepository {
	@PersistenceContext
    EntityManager entityManager;

	@Transactional
	public List<Product> getAllProduct() {
		String jpql="SELECT p FROM Product p";
		Query q = entityManager.createQuery(jpql);
		List<Product> list=q.getResultList();
		return list;
	}
	@Transactional
	public boolean isProductPresent(int id) {
		return ((Number)entityManager.createNamedQuery("is-product-present")
				.setParameter("pn", id)
				.getSingleResult()).intValue()== 1? true:false;
	}
	
	@Transactional
	public Product getProduct(int id) {
		Product prodProfile;
		try {
			prodProfile = entityManager.find(Product.class,id);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return prodProfile;
	}

	@Transactional
	public int save(Product product) {
		Product updatedProduct = (Product) entityManager.merge(product);
		return updatedProduct.getId();
	}
}

