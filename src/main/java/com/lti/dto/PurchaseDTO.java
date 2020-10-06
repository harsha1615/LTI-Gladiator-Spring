package com.lti.dto;

import java.time.LocalDateTime;
import java.util.List;

public class PurchaseDTO {
	
	private int id;
	private ProductDTO product;
	private float price;
	private int emiTenure;
	private float emiAmount;
	private int emisPaid;
	private LocalDateTime dateTime;
	private List<EmiPaymentDTO> emiPayments;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public ProductDTO getProduct() {
		return product;
	}
	public void setProduct(ProductDTO product) {
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
	public List<EmiPaymentDTO> getEmiPayments() {
		return emiPayments;
	}
	public void setEmiPayments(List<EmiPaymentDTO> emiPayments) {
		this.emiPayments = emiPayments;
	}
	
}
