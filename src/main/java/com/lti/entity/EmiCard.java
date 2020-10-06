package com.lti.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "LTIGL_EMI_CARD")
public class EmiCard {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "LTIGL_EMI_CARD_SQ")
	@SequenceGenerator(name = "LTIGL_EMI_CARD_SQ", sequenceName = "LTIGL_EMI_CARD_SQ", allocationSize = 1)
	private int id;
	@Column(name = "card_type")
	private CardType cardType;
	@Column(name = "card_no")
	private String cardNo;
	private boolean activated;
	private LocalDate validity;
	@Column(columnDefinition = "NUMBER(10,2)")
	private float balance;
	@Column(columnDefinition = "NUMBER(10,2)")
	private float limit; 
	@OneToOne(mappedBy = "emiCard")
	private User user;
	
	public static enum CardType {
		NONE, GOLD, TITANIUM
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public CardType getCardType() {
		return cardType;
	}
	public void setCardType(CardType cardType) {
		this.cardType = cardType;
	}
	public String getCardNo() {
		return cardNo;
	}
	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}
	public boolean isActivated() {
		return activated;
	}
	public void setActivated(boolean activated) {
		this.activated = activated;
	}
	public LocalDate getValidity() {
		return validity;
	}
	public void setValidity(LocalDate validity) {
		this.validity = validity;
	}
	public float getBalance() {
		return balance;
	}
	public void setBalance(float balance) {
		this.balance = balance;
	}
	public float getLimit() {
		return limit;
	}
	public void setLimit(float limit) {
		this.limit = limit;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}

}
