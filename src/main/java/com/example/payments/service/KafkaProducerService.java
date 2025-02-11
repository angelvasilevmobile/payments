package com.example.payments.service;

import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
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

	public KafkaProducerService(KafkaTemplate<String, String> kafkaTemplate) {
		super();
		this.kafkaTemplate = kafkaTemplate;
	}

	public void send(String topic, String message) {
//		this.kafkaTemplate.send(topic, message);
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
	
	private String buildPaymentMessage(Channel channel, PaymentRequest paymentRequest, Currency currency) {
		// TODO Auto-generated method stub
		return null;
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
