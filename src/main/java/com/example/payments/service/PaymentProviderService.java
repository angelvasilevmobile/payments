package com.example.payments.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.payments.dao.PaymentProviderEntity;
import com.example.payments.dao.PaymentProviderRepository;
import com.example.payments.endpoint.PaymentProvider;
import com.example.payments.endpoint.ProviderRequest;


@Service
public class PaymentProviderService {

	@Autowired
	private ModelMapper modelMapper;

	private PaymentProviderRepository paymentProviderRepository;

	public PaymentProviderService(PaymentProviderRepository paymentProviderRepository) {
		this.paymentProviderRepository = paymentProviderRepository;
	}

	public List<PaymentProvider> determineProviders(ProviderRequest providerRequest) {
		List<PaymentProviderEntity> availableProviders = paymentProviderRepository.findByLocationAndCurrency(providerRequest.getLocation(),
				providerRequest.getCurrency());
		
		return buildPaymentProviderDtos(availableProviders);
	}

	private List<PaymentProvider> buildPaymentProviderDtos(List<PaymentProviderEntity> availableProviders) {
		return availableProviders.stream().map(
				ppe -> {
					return modelMapper.map(ppe, PaymentProvider.class);
                }
		).collect(Collectors.toList());
	}

}
