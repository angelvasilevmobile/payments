package com.example.payments.dao;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class PaymentProviderEntity {

	@Id
	@GeneratedValue
	private Long id;
	@Column(name = "location")
	private String location;
	@Column(name = "currency")
	private String currency;
	@Column(name = "name")
	private String name;
}
