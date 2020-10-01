package com.lti.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "LTIGL_PRODUCT")
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "LTIGL_PRODUCT_SQ")
	@SequenceGenerator(name = "LTIGL_PRODUCT_SQ", sequenceName = "LTIGL_PRODUCT_SQ", allocationSize = 1)
	private int id;
	private String name;
	private String image;
	private String description;
	@Column(name = "original_price", columnDefinition = "NUMBER(10,2)")
	private float originalPrice;
	@Column(name = "profit_percent", columnDefinition = "NUMBER(10,2)")
	private float profitPercent;
	@Column(name = "final_price", columnDefinition = "NUMBER(10,2)")
	private float finalPrice;
	@OneToMany(mappedBy = "product")
	private List<Purchase> purchases;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public float getOriginalPrice() {
		return originalPrice;
	}
	public void setOriginalPrice(float originalPrice) {
		this.originalPrice = originalPrice;
	}
	public float getProfitPercent() {
		return profitPercent;
	}
	public void setProfitPercent(float profitPercent) {
		this.profitPercent = profitPercent;
	}
	public float getFinalPrice() {
		return finalPrice;
	}
	public void setFinalPrice(float finalPrice) {
		this.finalPrice = finalPrice;
	}
	public List<Purchase> getPurchases() {
		return purchases;
	}
	public void setPurchases(List<Purchase> purchases) {
		this.purchases = purchases;
	}
	
}
