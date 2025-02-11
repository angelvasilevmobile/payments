package com.example.payments.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class TaxProcessor {

		@KafkaListener(groupId = "taxes", topics = Topics.TAXES)
		public void handleKafkaMessage(String message) {
			//withdraw tax
		}
	}
