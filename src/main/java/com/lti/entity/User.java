package com.lti.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "LTIGL_USER")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "LTIGL_USER_SQ")
	@SequenceGenerator(name = "LTIGL_USER_SQ", sequenceName = "LTIGL_USER_SQ", allocationSize = 1)
	private int id;
	private String name;
	private String username;
	private String email;
	@Column(name = "phone_no")
	private String phoneNo;
	private String password;
	private String address;
	@Column(name = "paid_for_card")
	private boolean paidForCard;
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "card_id")
	private EmiCard emiCard;
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "bank_id")
	private UserBank bank;
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private List<Purchase> purchases;
	@OneToOne(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private UserPayment userPayment;
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
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
	public EmiCard getEmiCard() {
		return emiCard;
	}
	public void setEmiCard(EmiCard emiCard) {
		this.emiCard = emiCard;
	}
	public UserBank getBank() {
		return bank;
	}
	public void setBank(UserBank bank) {
		this.bank = bank;
	}
	public List<Purchase> getPurchases() {
		return purchases;
	}
	public void setPurchases(List<Purchase> purchases) {
		this.purchases = purchases;
	}
	public UserPayment getUserPayment() {
		return userPayment;
	}
	public void setUserPayment(UserPayment userPayment) {
		this.userPayment = userPayment;
	}
	
}
