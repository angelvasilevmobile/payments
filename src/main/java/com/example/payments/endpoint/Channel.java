package com.example.payments.endpoint;

import com.example.payments.dao.AntifraudProviderEntity;
import com.example.payments.dao.CurrencyEntity;
import com.example.payments.dao.PaymentProviderEntity;
import com.example.payments.dao.TaxesEntity;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Channel {

	private Long id;
	private AntifraudProvider antifraudProvider;
	private PaymentProvider paymentProvider;
	private List<Currency> supportedCurrencies;
	private List<Taxes> taxes;
	private String currency;
}
