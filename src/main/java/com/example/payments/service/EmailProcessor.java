package com.example.payments.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class EmailProcessor {

	@KafkaListener(groupId = "emails", topics = Topics.EMAILS)
	public void handleKafkaMessage(String message) {
		//send email on successfull payment event
	}
}
