package com.lti.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "LTIGL_USER_BANK")
public class UserBank {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "LTIGL_USER_BANK_SQ")
	@SequenceGenerator(name = "LTIGL_USER_BANK_SQ", sequenceName = "LTIGL_USER_BANK_SQ", allocationSize = 1)
	private int id;
	private String name;
	private String ifsc;
	@Column(name = "account_no")
	private String accountNo;
	@OneToOne(mappedBy = "bank")
	private User user;
	
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
	public String getIfsc() {
		return ifsc;
	}
	public void setIfsc(String ifsc) {
		this.ifsc = ifsc;
	}
	public String getAccountNo() {
		return accountNo;
	}
	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
}
