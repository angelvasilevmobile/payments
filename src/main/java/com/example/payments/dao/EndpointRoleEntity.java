package com.example.payments.dao;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class EndpointRoleEntity {

	@Id
	@GeneratedValue
	private Long id;
}
