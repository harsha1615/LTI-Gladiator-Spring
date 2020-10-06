package com.lti.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "ltigl_emi_payment")
public class EmiPayment {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "LTIGL_EMI_PAYMENT_SQ")
	@SequenceGenerator(name = "LTIGL_EMI_PAYMENT_SQ", sequenceName = "LTIGL_EMI_PAYMENT_SQ", allocationSize = 1)
	private int id;
	@ManyToOne
	@JoinColumn(name = "purchase_id")
	private Purchase purchase;
	@Column(name = "emi_no")
	private int emiNo;
	@Column(name = "emi_amount", columnDefinition = "NUMBER(10,2)")
	private float emiAmount;
	@Column(name = "late_fee", columnDefinition = "NUMBER(10,2)")
	private float lateFee;
	@Column(name = "total_amount", columnDefinition = "NUMBER(10,2)")
	private float totalAmount;
	@Column(name = "date_time")
	private LocalDateTime dateTime;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Purchase getPurchase() {
		return purchase;
	}
	public void setPurchase(Purchase purchase) {
		this.purchase = purchase;
	}
	public int getEmiNo() {
		return emiNo;
	}
	public void setEmiNo(int emiNo) {
		this.emiNo = emiNo;
	}
	public float getEmiAmount() {
		return emiAmount;
	}
	public void setEmiAmount(float emiAmount) {
		this.emiAmount = emiAmount;
	}
	public float getLateFee() {
		return lateFee;
	}
	public void setLateFee(float lateFee) {
		this.lateFee = lateFee;
	}
	public float getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(float totalAmount) {
		this.totalAmount = totalAmount;
	}
	public LocalDateTime getDateTime() {
		return dateTime;
	}
	public void setDateTime(LocalDateTime dateTime) {
		this.dateTime = dateTime;
	}
	
}
