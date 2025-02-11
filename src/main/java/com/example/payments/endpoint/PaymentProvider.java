package com.example.payments.endpoint;

public class PaymentProvider {

    private String name;
    private String currency;
    private String country;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public PaymentProvider(String name, String currency, String country) {
        this.name = name;
        this.currency = currency;
        this.country = country;
    }

    public PaymentProvider() {
    }
}
