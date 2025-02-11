package com.example.payments.dao;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TaxesDefinitionRepository extends JpaRepository<TaxesDefinitionEntity, Long>{

    public TaxesDefinitionEntity findByTransactionCurrencyAndReceiverCurrency(String transactionCurrency, String receiverCurrency);
}
