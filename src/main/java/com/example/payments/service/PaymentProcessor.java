package com.example.payments.service;

import java.math.BigDecimal;
import java.util.Date;

import com.example.payments.dao.*;
import com.example.payments.endpoint.ClientProfile;
import com.example.payments.endpoint.Currency;
import com.example.payments.endpoint.PaymentRequest;
import com.example.payments.provider.DefaultProvider;
import com.example.payments.provider.Stripe;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
@Transactional
public class PaymentProcessor {

	private ClientProfileRepository clientProfileRepository;
	private TransactionRepository transactionRepository;
	private TaxService taxService;
	private PaymentProviderRepository paymentProviderRepository;
	private AntifraudProviderRepository antifraudProviderRepository;
	private KafkaProducerService topicProducer;
	private Logger logger = LoggerFactory.getLogger(getClass());

	public PaymentProcessor(ClientProfileRepository clientProfileRepository,
			TaxService taxesService,
			TransactionRepository transactionRepository,
			PaymentProviderRepository paymentProviderRepository,
			AntifraudProviderRepository antifraudProviderRepository,
			KafkaProducerService topicProducer) {
		this.clientProfileRepository = clientProfileRepository;
		this.transactionRepository = transactionRepository;
		this.paymentProviderRepository = paymentProviderRepository;
		this.antifraudProviderRepository = antifraudProviderRepository;
		this.taxService = taxesService;
		this.topicProducer = topicProducer;
	}

	@KafkaListener(groupId = "payments", topics = Topics.PAYMENTS)
	public String handleKafkaMessage(String json) {
		//withdraw money, transfer money, call payment provider
		try {
			PaymentRequest paymentRequest = new ObjectMapper().readValue(json, PaymentRequest.class);

			ClientProfile sender = paymentRequest.getSender();
			ClientProfileEntity senderEntity = clientProfileRepository.findByUsername(sender.getUsername());
			ClientProfile receiver = paymentRequest.getReceiver();
			ClientProfileEntity receiverEntity = clientProfileRepository.findByUsername(receiver.getUsername());
			
			BigDecimal paymentAmount = (BigDecimal) paymentRequest.getAmount();
			Currency transactionCurrency = paymentRequest.getCurrency();
			String receiverCurrency = receiverEntity.getCurrency();
			BigDecimal taxesAmount = taxService.calculateTax(paymentAmount, transactionCurrency.toString(), receiverCurrency);
			BigDecimal fullAmount = paymentAmount.add(taxesAmount);

			PaymentProviderEntity paymentProvider = paymentProviderRepository.findByName(paymentRequest.getProvider().getName());

//			AntifraudProviderEntity antifraudProvider = antifraudProviderRepository.findByName(channel.getAtifraudProvider());

			TransactionEntity transaction = new TransactionEntity();
			transaction.setSender(senderEntity);
			transaction.setReceiver(receiverEntity);
			transaction.setAmount(paymentAmount);
			transaction.setFee(taxesAmount);
			transaction.setDate(new Date());
			transaction.setPaymentProvider(paymentProvider);
			transaction.setAntifraudProvider(null);
			transactionRepository.save(transaction);
			
			senderEntity.setBalance(senderEntity.getBalance().subtract(fullAmount));
			clientProfileRepository.save(senderEntity);
			
			receiverEntity.setBalance(receiverEntity.getBalance().add(paymentAmount));
			clientProfileRepository.save(receiverEntity);
			
			String topic = paymentProvider.getName();
//			topicProducer.send(topic, buildProviderSpecificJson(topic, paymentRequest));

			return "success";
		} catch (Exception e) {
			logger.error("Payment processing failed." , e);
			return "failure";
		}
	}

	private String buildProviderSpecificJson(String topic, PaymentRequest paymentRequest) {
		ObjectMapper jsonMapper = new ObjectMapper();
		String providerName = paymentRequest.getProvider().getName();
		String result = null;
		try {
			switch (providerName) {
				case "STRIPE" :
					Stripe stripe = new Stripe();
					stripe.setName(paymentRequest.getProvider().getName());
					stripe.setCurrency(paymentRequest.getCurrency().toString());
					result = jsonMapper.convertValue(stripe, Stripe.class).toString();
					break;
				default:
					DefaultProvider defaultProvider = new DefaultProvider("DEFAULT-PROVIDER", "BGN");
					result = jsonMapper.convertValue(defaultProvider, DefaultProvider.class).toString();
					break;
			}
		} catch (Exception e) {
			logger.error("Unable to parse provider specific json.", e);
		}

		return result;
	}
}
