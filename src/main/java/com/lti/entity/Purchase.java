package com.lti.entity;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "LTIGL_PURCHASE")
public class Purchase {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "LTIGL_PURCHASE_SQ")
	@SequenceGenerator(name = "LTIGL_PURCHASE_SQ", sequenceName = "LTIGL_PURCHASE_SQ", allocationSize = 1)
	private int id;
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
	@OneToOne
	@JoinColumn(name = "product_id")
	private Product product;
	@Column(columnDefinition = "NUMBER(10,2)")
	private float price;
	@Column(name = "emi_tenure")
	private int emiTenure;
	@Column(name = "emi_amount", columnDefinition = "NUMBER(10,2)")
	private float emiAmount;
	@Column(name = "emis_paid")
	private int emisPaid;
	@Column(name = "date_time")
	private LocalDateTime dateTime;
	@OneToMany(mappedBy = "purchase")
	private List<EmiPayment> emiPayments;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public int getEmiTenure() {
		return emiTenure;
	}
	public void setEmiTenure(int emiTenure) {
		this.emiTenure = emiTenure;
	}
	public float getEmiAmount() {
		return emiAmount;
	}
	public void setEmiAmount(float emiAmount) {
		this.emiAmount = emiAmount;
	}
	public int getEmisPaid() {
		return emisPaid;
	}
	public void setEmisPaid(int emisPaid) {
		this.emisPaid = emisPaid;
	}
	public LocalDateTime getDateTime() {
		return dateTime;
	}
	public void setDateTime(LocalDateTime dateTime) {
		this.dateTime = dateTime;
	}
	public List<EmiPayment> getEmiPayments() {
		return emiPayments;
	}
	public void setEmiPayments(List<EmiPayment> emiPayments) {
		this.emiPayments = emiPayments;
	}
	
}
