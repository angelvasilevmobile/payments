package com.example.payments.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;


@Service
public class PaymentAttemptProcessor {

	@KafkaListener(groupId = "payment-attempts", topics = Topics.PAYMENT_ATTEMPTS)
	public void handleKafkaMessage(String message) {
		//send email on successfull payment event
	}
}
