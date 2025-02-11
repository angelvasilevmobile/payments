package com.example.payments.endpoint;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ClientProfile {

    private String username;
    private Currency currency;
    private String country;
    private String address;

}
