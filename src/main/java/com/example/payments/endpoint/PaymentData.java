package com.example.payments.endpoint;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PaymentData {

    private Long id;
    private PaymentProvider paymentProvider;
    private String currency;
    private BigDecimal amount;
    private BigDecimal tax;
    private ClientProfile sender;
    private ClientProfile receiver;
}
