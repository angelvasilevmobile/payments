package com.example.payments.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.payments.endpoint.Currency;

public interface ChannelRepository extends JpaRepository<ChannelEntity, Long>{

	List<ChannelEntity> findByPaymentProviderAndCurrency(PaymentProviderEntity paymentProvider, String currency);

}
