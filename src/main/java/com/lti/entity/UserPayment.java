package com.lti.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "LTIGL_USER_PAYMENT")
public class UserPayment {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "LTIGL_USER_PAYMENT_SQ")
	@SequenceGenerator(name = "LTIGL_USER_PAYMENT_SQ", sequenceName = "LTIGL_USER_PAYMENT_SQ", allocationSize = 1)
	private int id;
	@OneToOne
	@JoinColumn(name = "user_id")
	private User user;
	@Column(columnDefinition = "NUMBER(10,2)")
	private float amount;
	@Column(name = "date_time")
	private LocalDateTime dateTime;
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
	public float getAmount() {
		return amount;
	}
	public void setAmount(float amount) {
		this.amount = amount;
	}
	public LocalDateTime getDateTime() {
		return dateTime;
	}
	public void setDateTime(LocalDateTime dateTime) {
		this.dateTime = dateTime;
	}
	
}
