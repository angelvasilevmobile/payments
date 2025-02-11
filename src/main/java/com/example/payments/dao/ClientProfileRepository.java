package com.example.payments.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.payments.endpoint.ClientProfile;

public interface ClientProfileRepository extends JpaRepository<ClientProfileEntity, Long>{

	ClientProfileEntity findByUsername(String email);

}
