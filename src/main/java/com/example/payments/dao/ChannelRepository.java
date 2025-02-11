package com.example.payments.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.payments.endpoint.Currency;
import com.example.payments.endpoint.PaymentProvider;

public interface ChannelRepository extends JpaRepository<ChannelEntity, Long>{

	List<ChannelEntity> findByPaymentProviderAndCurrency(PaymentProvider paymentProvider, Currency currency);

}
