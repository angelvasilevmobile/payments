package com.example.payments.service;

import java.util.Properties;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.example.payments.endpoint.Channel;
import com.example.payments.endpoint.Currency;
import com.example.payments.endpoint.PaymentRequest;


@Service
public class KafkaProducerService {

	static Properties props = new Properties();
	 static {
		 props.put("bootstrap.servers", "localhost:9092");
		 props.put("acks", "all");
		 props.put("retries", 0);
		 props.put("linger.ms", 1);
		 props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		 props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
	 }

	private Producer<String, String> kafkaProducer = new KafkaProducer<>(props);
	private KafkaTemplate<String, String> kafkaTemplate;
	private ObjectMapper objectMapper = new ObjectMapper();
	private Logger logger = LoggerFactory.getLogger(getClass());

	public KafkaProducerService(KafkaTemplate<String, String> kafkaTemplate) {
		super();
		this.kafkaTemplate = kafkaTemplate;
	}

	public void send(String topic, String message) {
//		this.kafkaTemplate.send(topic, message);
		logger.debug("Sending payload to kafka topic: " + topic + " , message: " +message );
		this.kafkaProducer.send(new ProducerRecord<String, String>(topic, message));
	}

	public void sendPayEvent(Channel channel, PaymentRequest paymentRequest, Currency currency) {
		this.send(Topics.PAYMENTS, buildPaymentMessage(channel, paymentRequest, currency));
	}

	public void sendCollectTaxEvent(Channel channel, PaymentRequest paymentRequest, Currency currency) {
		this.send(Topics.TAXES, buildTaxesMessage(channel, paymentRequest, currency));
	}
	
	public void sendEmailEvent(Channel channel, PaymentRequest paymentRequest, Currency currency) {
		this.send(Topics.EMAILS, buildEmailsMessage(channel, paymentRequest, currency));
	}
	
	public void sendPaymentAttemptEvent(Channel channel, PaymentRequest paymentRequest, Currency currency) {
		this.send(Topics.PAYMENT_ATTEMPTS, buildPaymentMessage(channel, paymentRequest, currency));
	}
	
	public void sendPaymentFailureEvent(Channel channel, PaymentRequest paymentRequest, Currency currency) {
		this.send(Topics.PAYMENT_FAILURES, buildPaymentFailureMessage(channel, paymentRequest, currency));
	}
	
	public void sendAntifraudFailureEvent(Channel channel, PaymentRequest paymentRequest, Currency currency) {
		this.send(Topics.ANTIFRAUD_FAILURES, buildAntifraudFailureMessage(channel, paymentRequest, currency));
	}
	
	
	

	private String buildPaymentFailureMessage(Channel channel, PaymentRequest paymentRequest, Currency currency) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public String buildPaymentMessage(Channel channel, PaymentRequest paymentRequest, Currency currency) {
        try {
            return objectMapper.writeValueAsString(paymentRequest);
        } catch (JsonProcessingException e) {
            logger.error("Invalid payment json.");
        }
        return "Invalid payment json.";
    }

	private String buildTaxesMessage(Channel channel, PaymentRequest paymentRequest, Currency currency) {
		// TODO Auto-generated method stub
		return null;
	}

	private String buildEmailsMessage(Channel channel, PaymentRequest paymentRequest, Currency currency) {
		// TODO Auto-generated method stub
		return null;
	}


	private String buildAntifraudFailureMessage(Channel channel, PaymentRequest paymentRequest, Currency currency) {
		// TODO Auto-generated method stub
		return null;
	}

}
