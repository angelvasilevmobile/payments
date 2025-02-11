package com.example.payments.provider;

import java.math.BigDecimal;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class Provider1Client {

	public ResponseEntity<Object> getProfile() {
		return null;
	}
	public ResponseEntity<Object> sendAmount(BigDecimal amount, String provider1SenderUsername, String provider1ReceiverUsername) {
		return null;
	}
}
