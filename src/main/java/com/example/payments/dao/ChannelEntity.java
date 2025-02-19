package com.example.payments.dao;

import java.util.List;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class ChannelEntity {

	@Id
	@GeneratedValue
	private Long id;
	@OneToOne
	private AntifraudProviderEntity antifraudProvider;
	@OneToOne
	private PaymentProviderEntity paymentProvider;
	@Column
	private String supportedCurrencies;
	@ManyToMany(mappedBy = "channels")
	private List<TaxesEntity> taxes;
	@Column(name = "currency")
	private String currency;


	
}
