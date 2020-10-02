package com.lti.dto;

public class UserProfileDTO {
	
	private int id;
	private String name;
	private String username;
	private String phone;
	private String email;
	private String address;
	private boolean paidForCard;
	private UserEmiCardDTO emiCard;
	
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
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public boolean isPaidForCard() {
		return paidForCard;
	}
	public void setPaidForCard(boolean paidForCard) {
		this.paidForCard = paidForCard;
	}
	public UserEmiCardDTO getEmiCard() {
		return emiCard;
	}
	public void setEmiCard(UserEmiCardDTO emiCard) {
		this.emiCard = emiCard;
	}
	
	
}
