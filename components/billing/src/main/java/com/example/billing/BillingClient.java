package com.example.billing;


import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestOperations;

import java.util.logging.Logger;

public class BillingClient {
    private org.slf4j.Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    RestTemplate restTemplate;
    String billingEndpoint;

    public BillingClient(String billingEndpoint){
        this.billingEndpoint = billingEndpoint;
    }

    @HystrixCommand(fallbackMethod = "defaultBilling")
    public void billUser(String userId, int paymentMonthlyAmount) {
        logger.info("inside billUser");
        restTemplate.postForEntity(billingEndpoint, paymentMonthlyAmount, String.class);
        logger.info("after billUser");
        logger.info("User Id:" + userId + "paymentAmount: " + paymentMonthlyAmount + "IS REAL");
    }

    public void defaultBilling(String userId, int paymentMonthlyAmount) {
        logger.info("User Id:" + userId + "paymentAmount: " + paymentMonthlyAmount + "has failed");

    }
}
