package com.example.payments;

import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.rabbitmq.client.ConnectionFactory;

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
}
