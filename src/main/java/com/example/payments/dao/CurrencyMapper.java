package com.example.payments.dao;


import org.mapstruct.Mapper;

@Mapper
public interface CurrencyMapper {

    CurrencyDto toCurrencyDto(CurrencyEntity currencyEntity);

    CurrencyEntity toCurrencyEntity(CurrencyDto currencyDto);
}
