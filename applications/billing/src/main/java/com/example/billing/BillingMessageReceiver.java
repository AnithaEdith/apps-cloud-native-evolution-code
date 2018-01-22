package com.example.billing;

import com.example.payments.Gateway;
import org.springframework.messaging.handler.annotation.Payload;

public class BillingMessageReceiver {
    private Gateway paymentGateway;
    public BillingMessageReceiver(Gateway paymentGateway) {

        this.paymentGateway = paymentGateway;
    }

    public void process(@Payload BillingMessage message) {
        int amount = message.getAmount();
        if (paymentGateway.createReocurringPayment(amount)){
            System.out.println("Received message with sucess");
        } else {
            System.out.println("Failed Message");
        }

    }
}
