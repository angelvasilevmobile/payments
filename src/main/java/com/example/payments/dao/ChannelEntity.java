package com.example.payments.dao;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;

@Entity
public class ChannelEntity {

	@Id
	@GeneratedValue
	private Long id;
	@OneToOne
	private AntifraudProviderEntity antifraudProvider;
	@OneToOne
	private PaymentProviderEntity paymentProvider;
	@ManyToMany(mappedBy = "channels")
	private List<CurrencyEntity> supportedCurrencies;
	@ManyToMany(mappedBy = "channels")
	private List<TaxesEntity> taxes;
	@Column(name = "currency")
	private String currency;

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public AntifraudProviderEntity getAntifraudProvider() {
		return antifraudProvider;
	}
	public void setAntifraudProvider(AntifraudProviderEntity antifraudProvider) {
		this.antifraudProvider = antifraudProvider;
	}
	public PaymentProviderEntity getPaymentProvider() {
		return paymentProvider;
	}
	public void setPaymentProvider(PaymentProviderEntity paymentProvider) {
		this.paymentProvider = paymentProvider;
	}
	public List<CurrencyEntity> getSupportedCurrencies() {
		return supportedCurrencies;
	}
	public void setSupportedCurrencies(List<CurrencyEntity> supportedCurrencies) {
		this.supportedCurrencies = supportedCurrencies;
	}
	public List<TaxesEntity> getTaxes() {
		return taxes;
	}
	public void setTaxes(List<TaxesEntity> taxes) {
		this.taxes = taxes;
	}
	public ChannelEntity() {
		super();
	}
	
	
}
