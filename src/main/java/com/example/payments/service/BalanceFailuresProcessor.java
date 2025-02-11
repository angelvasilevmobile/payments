package com.example.payments.service;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class BalanceFailuresProcessor {

	private RabbitTemplate rabbitTemplate;
	
	
	public BalanceFailuresProcessor(RabbitTemplate rabbitTemplate) {
		super();
		this.rabbitTemplate = rabbitTemplate;
	}

	public void produce(String message) {
		this.rabbitTemplate.send(new Message(message.getBytes()));
	}

//	@KafkaListener(groupId = "balance-failures", topics = Topics.FAILURES)
	@RabbitListener(queues = {Topics.FAILURES})
	public void handleKafkaMessage(String message) {
		//store in db all failure events
	}
}
