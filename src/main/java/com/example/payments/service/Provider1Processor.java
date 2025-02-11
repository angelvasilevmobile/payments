package com.example.payments.service;

import java.math.BigDecimal;
import java.util.Map;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.example.payments.provider.Provider1Client;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class Provider1Processor {

	private Provider1Client client;
	
	public Provider1Processor(Provider1Client client) {
		this.client = client;
	}
	
	@KafkaListener(groupId = Topics.PROVIDER1, topics = Topics.PROVIDER1)
	public void handleKafkaMessage(String json) {
		Map<String, ?> parsedJson = null;
		try {
			parsedJson = new ObjectMapper().readValue(json, Map.class);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		client.sendAmount((BigDecimal)parsedJson.get("amount"), (String)parsedJson.get("sender-username"), (String)parsedJson.get("receiver-username"));
	}
}
