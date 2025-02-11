package com.example.payments.endpoint;

import java.math.BigDecimal;

public class PaymentRequest {

	private PaymentProvider provider;
	private Currency currency;
	private ClientProfile sender;
	private ClientProfile receiver;
	private BigDecimal amount;
	private AntifraudProvider antifraudProvider;

	public PaymentProvider getProvider() {
		return provider;
	}

	public void setProvider(PaymentProvider provider) {
		this.provider = provider;
	}

	public Currency getCurrency() {
		return currency;
	}

	public void setCurrency(Currency currency) {
		this.currency = currency;
	}

	public ClientProfile getSender() {
		return sender;
	}

	public void setSender(ClientProfile sender) {
		this.sender = sender;
	}

	public ClientProfile getReceiver() {
		return receiver;
	}

	public void setReceiver(ClientProfile receiver) {
		this.receiver = receiver;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public AntifraudProvider getAntifraudProvider() {
		return antifraudProvider;
	}

	public void setAntifraudProvider(AntifraudProvider antifraudProvider) {
		this.antifraudProvider = antifraudProvider;
	}

	public PaymentRequest() {
	}
}
