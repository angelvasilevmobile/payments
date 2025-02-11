package com.example.payments.service;

import java.math.BigDecimal;
import java.math.RoundingMode;

import com.example.payments.dao.TaxesDefinitionEntity;
import com.example.payments.dao.TaxesDefinitionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaxService {

	@Autowired
	private TaxesDefinitionRepository repository;

	public BigDecimal calculateTax(BigDecimal paymentAmount, String transactionCurrency, String receiverCurrency) {
		TaxesDefinitionEntity taxes = repository.findByTransactionCurrencyAndReceiverCurrency(transactionCurrency, receiverCurrency);
		BigDecimal percentage = taxes.getPercentage();

		return paymentAmount.multiply(percentage).divide(new BigDecimal("100"), 2, RoundingMode.HALF_UP);
		
	}
}
