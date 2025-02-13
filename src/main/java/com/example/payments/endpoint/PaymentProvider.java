package com.example.payments.endpoint;

public class PaymentProvider {

    private String name;
    private String currency;
    private String location;

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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public PaymentProvider(String name, String currency, String location) {
        this.name = name;
        this.currency = currency;
        this.location = location;
    }

    public PaymentProvider() {
    }
}
