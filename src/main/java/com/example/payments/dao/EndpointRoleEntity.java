package com.example.payments.dao;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class EndpointRoleEntity {

	@Id
	@GeneratedValue
	private Long id;
}
