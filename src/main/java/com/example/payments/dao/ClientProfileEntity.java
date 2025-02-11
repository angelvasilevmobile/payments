package com.example.payments.dao;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class ClientProfileEntity {

	@Id
	@GeneratedValue
	private Long id;
	@ManyToMany(mappedBy = "clientProfiles")
	private List<RoleEntity> roles;
	@Column(name = "balance")
	private BigDecimal balance;
	@Column(name = "username")
	private String username;
	
	
	public BigDecimal getBalance() {
		return this.balance;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public List<RoleEntity> getRoles() {
		return roles;
	}


	public void setRoles(List<RoleEntity> roles) {
		this.roles = roles;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

}
