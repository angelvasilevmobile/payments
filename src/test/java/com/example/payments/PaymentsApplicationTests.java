package com.example.payments;

import com.example.payments.dao.*;
import com.example.payments.endpoint.ClientProfile;
import com.example.payments.endpoint.Currency;
import com.example.payments.endpoint.PaymentProvider;
import com.example.payments.endpoint.PaymentRequest;
import com.example.payments.service.KafkaProducerService;
import com.example.payments.service.PaymentProcessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

@SpringBootTest
class PaymentsApplicationTests {

	@Autowired
	private PaymentProcessor processor;
	@Autowired
	private KafkaProducerService kafkaProducerService;
	@Autowired
	private PaymentProviderRepository paymentProviderRepository;
	@Autowired
	private ClientProfileRepository clientProfileRepository;
	@Autowired
	private TaxesDefinitionRepository taxesDefinitionRepository;

	@Test
	void contextLoads() {
	}

	@BeforeEach
    void uploadEntities() {
		ClientProfileEntity sender = new ClientProfileEntity(null, null, new BigDecimal(5000l), "sender@sender.sender", "BG", "Address 1", "BGN");
		ClientProfileEntity receiver = new ClientProfileEntity(null, null, new BigDecimal(5000l), "receiver@receiver.receiver", "BG", "Address 2", "BGN");
//		PaymentProviderEntity provider = new PaymentProviderEntity(2l, "BG", "BGN", "STRIPE");
		TaxesDefinitionEntity taxes = new TaxesDefinitionEntity(1l, new BigDecimal(2l), "BGN", "BGN");
		clientProfileRepository.save(sender);
		clientProfileRepository.save(receiver);
//		paymentProviderRepository.save(provider);
		taxesDefinitionRepository.save(taxes);
	}

	@Test
	void paymentProcessorTest() {
		//GIVEN
		PaymentRequest paymentRequest = new PaymentRequest();
		paymentRequest.setAmount(new BigDecimal(20l));
		paymentRequest.setCurrency(Currency.BGN);
		PaymentProvider paymentProvider = new PaymentProvider();
		paymentProvider.setName("STRIPE");
		paymentProvider.setCurrency(String.valueOf(Currency.BGN));
		paymentProvider.setLocation("BG");
		paymentRequest.setProvider(paymentProvider);
		ClientProfile sender = new ClientProfile();
		sender.setUsername("sender@sender.sender");
		sender.setCurrency(Currency.BGN);
		sender.setCountry("BG");
		paymentRequest.setSender(sender);
		ClientProfile receiver = new ClientProfile();
		receiver.setUsername("receiver@receiver.receiver");
		receiver.setCurrency(Currency.BGN);
		receiver.setCountry("BG");
		paymentRequest.setReceiver(receiver);

		//WHEN
		String result = processor.handleKafkaMessage(kafkaProducerService.buildPaymentMessage(null, paymentRequest, Currency.BGN));

		//THEN
		Assertions.assertNotNull(result);
		Assertions.assertEquals("success", result);
	}
}
