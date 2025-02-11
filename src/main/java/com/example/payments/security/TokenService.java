package com.example.payments.security;

import org.springframework.stereotype.Service;

import com.example.payments.dao.ClientProfileEntity;
import com.example.payments.dao.ClientProfileRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class TokenService {

	private ClientProfileRepository clientProfileRepository;
	private ObjectMapper objectMapper = new ObjectMapper();

	private TokenService(ClientProfileRepository clientProfileRepository) {
		this.clientProfileRepository = clientProfileRepository;
	}

	public String generateToken(String email) {
		ClientProfileEntity client = clientProfileRepository.findByUsername(email);
		
		return generateToken(client);
	}

	private String generateToken(ClientProfileEntity client) {
		

		return null;
	}
}
