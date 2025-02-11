package com.example.payments.dao;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PaymentProviderRepository extends JpaRepository<PaymentProviderEntity, Long>{

	List<PaymentProviderEntity> findByLocationAndCurrency(String location, String currency);

    PaymentProviderEntity findByName(String name);

    @Query("SELECT DISTINCT provider FROM PaymentProviderEntity provider WHERE provider.name LIKE :name%")
    Collection<PaymentProviderEntity> findByNameQuery(@Param("name") String name);
}
