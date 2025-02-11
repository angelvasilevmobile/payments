package com.example.payments.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

import com.example.payments.dao.*;
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
	public void handleKafkaMessage(String json) {
		//withdraw money, transfer money, call payment provider
		try {
			Map<String, ?> parsedJson = new ObjectMapper().readValue(json, Map.class);
			
			String senderUsername = (String) parsedJson.get("sender-username");
			ClientProfileEntity sender = clientProfileRepository.findByUsername(senderUsername);
			String receiverUsername = (String) parsedJson.get("receiver-username");
			ClientProfileEntity receiver = clientProfileRepository.findByUsername(receiverUsername);
			
			BigDecimal paymentAmount = (BigDecimal) parsedJson.get("amount");
			String transactionCurrency = (String) parsedJson.get("currency");
			String receiverCurrency = (String) parsedJson.get("receiver-currency");
			BigDecimal taxesAmount = taxService.calculateTax(paymentAmount, transactionCurrency, receiverCurrency);
			BigDecimal fullAmount = paymentAmount.add(taxesAmount);

			PaymentProviderEntity paymentProvider = paymentProviderRepository.findByName((String) parsedJson.get("provider-name"));

			AntifraudProviderEntity antifraudProvider = antifraudProviderRepository.findByName((String) parsedJson.get("antifraud-provider-name"));

			TransactionEntity transaction = new TransactionEntity();
			transaction.setSender(sender);
			transaction.setReceiver(receiver);
			transaction.setAmount(paymentAmount);
			transaction.setFee(taxesAmount);
			transaction.setDate(new Date());
			transaction.setPaymentProvider(paymentProvider);
			transaction.setAntifraudProvider(antifraudProvider);
			transactionRepository.save(transaction);
			
			sender.setBalance(sender.getBalance().subtract(fullAmount));
			clientProfileRepository.save(sender);
			
			receiver.setBalance(receiver.getBalance().add(paymentAmount));
			clientProfileRepository.save(receiver);
			
			String topic = (String) parsedJson.get("provider");
			topicProducer.send(topic, buildProviderSpecificJson(parsedJson));
			
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private String buildProviderSpecificJson(Map<String, ?> parsedJson) {
		// TODO Auto-generated method stub
		return null;
	}
}
