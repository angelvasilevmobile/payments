package com.example.payments.dao;

import java.math.BigDecimal;
import java.util.Date;

import jakarta.persistence.*;

@Entity
@Table(name = "transactions")
public class TransactionEntity {

	@Id
	@GeneratedValue
	private Long id;
	@OneToOne
	private ClientProfileEntity sender;
	@OneToOne
	private ClientProfileEntity receiver;
	@OneToOne
	private PaymentProviderEntity paymentProvider;
	@OneToOne
	private AntifraudProviderEntity antifraudProvider;
	@Column
	private BigDecimal fee;
	@Column
	private BigDecimal amount;
	@Column
	private Date date;

	public ClientProfileEntity getSender() {
		return sender;
	}
	public void setSender(ClientProfileEntity sender) {
		this.sender = sender;
	}
	public ClientProfileEntity getReceiver() {
		return receiver;
	}
	public void setReceiver(ClientProfileEntity receiver) {
		this.receiver = receiver;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}

	public BigDecimal getFee() {
		return fee;
	}

	public void setFee(BigDecimal fee) {
		this.fee = fee;
	}

	public PaymentProviderEntity getPaymentProvider() {
		return paymentProvider;
	}

	public void setPaymentProvider(PaymentProviderEntity paymentProvider) {
		this.paymentProvider = paymentProvider;
	}

	public AntifraudProviderEntity getAntifraudProvider() {
		return antifraudProvider;
	}

	public void setAntifraudProvider(AntifraudProviderEntity antifraudProvider) {
		this.antifraudProvider = antifraudProvider;
	}

	public TransactionEntity() {
	}

}
