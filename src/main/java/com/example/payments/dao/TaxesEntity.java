package com.example.payments.dao;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.*;

@Entity
public class TaxesEntity {

	@Id
	@GeneratedValue
	private Long id;
	@Column
	private BigDecimal amount;

	@ManyToMany()
	private List<ChannelEntity> channels;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public List<ChannelEntity> getChannels() {
		return channels;
	}

	public void setChannels(List<ChannelEntity> channels) {
		this.channels = channels;
	}

	public TaxesEntity() {
	}
}
