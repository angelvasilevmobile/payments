package com.example.payments.dao;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

@Entity
public class RoleEntity {

	@Id
	@GeneratedValue
	private Long id;
	@ManyToMany()
	public List<ClientProfileEntity> clientProfiles;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public List<ClientProfileEntity> getClientProfiles() {
		return clientProfiles;
	}
	public void setClientProfiles(List<ClientProfileEntity> clientProfiles) {
		this.clientProfiles = clientProfiles;
	}
	public RoleEntity() {
		super();
	}
	
	
}
