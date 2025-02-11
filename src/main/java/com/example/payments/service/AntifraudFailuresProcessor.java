package com.example.payments.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class AntifraudFailuresProcessor {

	@KafkaListener(groupId = "antifraud-failures", topics = Topics.ANTIFRAUD_FAILURES)
	public void handleKafkaMessage(String message) {
		//store in db all failure events
	}
}
