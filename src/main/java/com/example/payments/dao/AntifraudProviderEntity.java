package com.example.payments.dao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "antifraud_provider")
public class AntifraudProviderEntity {

	@Id
	@GeneratedValue
	private Long id;

	@Column
	private String name;
	
	public AntifraudProviderEntity() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}
