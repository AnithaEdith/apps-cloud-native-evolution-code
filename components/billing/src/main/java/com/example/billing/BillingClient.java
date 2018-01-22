package com.example.billing;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestOperations;

public class BillingClient {

    @Autowired
    RestTemplate restTemplate;
    String billingEndpoint;

    public BillingClient(String billingEndpoint){
        this.billingEndpoint = billingEndpoint;
    }

    public void billUser(String userId, int paymentMonthlyAmount) {
        restTemplate.postForEntity(billingEndpoint, paymentMonthlyAmount, String.class);
    }

}
