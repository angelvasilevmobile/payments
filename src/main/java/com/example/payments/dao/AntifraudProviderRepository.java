package com.example.payments.dao;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AntifraudProviderRepository extends JpaRepository<AntifraudProviderEntity, Long>{

    AntifraudProviderEntity findByName(String name);
}
