package com.example.payments.dao;

import java.util.List;

import javax.persistence.*;

@Entity
public class CurrencyEntity {

	@Id
	@GeneratedValue
	private Long id;
	@org.springframework.data.annotation.Transient
	@ManyToMany()
	private List<ChannelEntity> channels;
}
