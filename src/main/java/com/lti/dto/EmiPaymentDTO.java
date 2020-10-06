package com.lti.dto;

import java.time.LocalDateTime;

public class EmiPaymentDTO {
	
	private int emiNo;
	private float emiAmount;
	private float lateFee;
	private float totalAmount;
	private LocalDateTime dateTime;
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
