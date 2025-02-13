package com.example.payments.dao;

import java.math.BigDecimal;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
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
	@Column
	private String country;
	@Column
	private String address;
	@Column
	private String currency;
}
