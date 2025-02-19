package com.example.payments.endpoint;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PaymentRequest {

	private PaymentProvider provider;
	private Currency currency;
	private ClientProfile sender;
	private ClientProfile receiver;
	private BigDecimal amount;
//	private AntifraudProvider antifraudProvider;

}
