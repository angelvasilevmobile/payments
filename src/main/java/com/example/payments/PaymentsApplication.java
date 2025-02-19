package com.example.payments;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.Properties;

import com.example.payments.dao.*;
import com.fasterxml.jackson.databind.JsonSerializer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.modelmapper.ModelMapper;
//import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
//import org.springframework.web.servlet.config.annotation.EnableWebMvc;

//import com.rabbitmq.client.ConnectionFactory;

//@EnableWebMvc
@Configuration
@ComponentScan(basePackages = "com.example.payments")
@SpringBootApplication
public class PaymentsApplication {

	public static void main(String[] args) {
		SpringApplication.run(PaymentsApplication.class, args);
	}

	@Bean
	public KafkaTemplate<String, String> kafkaTemplate() {
		return new KafkaTemplate<String, String>(new ProducerFactory<String, String>(){

			@Override
			public Producer<String, String> createProducer() {
				 Properties props = new Properties();
				 
				 props.put("bootstrap.servers", "localhost:9092");
				 props.put("acks", "all");
				 props.put("retries", 0);
				 props.put("linger.ms", 1);
				 props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
				 props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
				
				return new KafkaProducer<>(props);
			}});
	}
//	
//	@Bean
//	public RabbitTemplate rabbitTemplate() {
//		return new RabbitTemplate();
//	}

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

//	@Bean
//	public ProducerFactory<String, Object> producerFactory() {
//		Map<String, Object> configProps = new HashMap<>();
//		configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
//		configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
//		configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
//		return new DefaultKafkaProducerFactory<>(configProps);
//	}

	@Bean
	public CommandLineRunner loadData(PaymentProviderRepository paymentProviderRepository,
									  PaymentAttemptRepository paymentAttemptRepository,
									  ChannelRepository channelRepository) {
		return args -> {
			Logger logger = LoggerFactory.getLogger(getClass());
			if (paymentProviderRepository.count() != 0L) {
				logger.info("Using existing database");
				return;
			}
			PaymentProviderEntity paymentProviderEntity =
					new PaymentProviderEntity(1l, "BG", "BGN", "STRIPE");
			paymentProviderRepository.save(paymentProviderEntity);
			logger.info("Payment provider created.");

			ClientProfileEntity sender = new ClientProfileEntity(null, null, new BigDecimal(5000l), "sender@payments.com", "BG", "Address 1", "BGN");
			ClientProfileEntity receiver = new ClientProfileEntity(null, null, new BigDecimal(5000l), "receiver@payments.com", "BG", "Address 2", "BGN");
//			PaymentProviderEntity provider = new PaymentProviderEntity(2l, "BG", "BGN", "STRIPE2");
			PaymentAttemptEntity payment = new PaymentAttemptEntity(1l, paymentProviderEntity, "BGN", new BigDecimal(50l),
					new BigDecimal(5l), sender, receiver);
			paymentAttemptRepository.save(payment);
			logger.info("Payment attempt created.");

			ChannelEntity channelEntity = new ChannelEntity();
			channelEntity.setPaymentProvider(paymentProviderEntity);
			String supportedCurrencies = "BGN";
			channelEntity.setSupportedCurrencies(supportedCurrencies);
			channelEntity.setCurrency("BGN");
			channelRepository.save(channelEntity);
			logger.info("Channel created.");
		};
	}
}
