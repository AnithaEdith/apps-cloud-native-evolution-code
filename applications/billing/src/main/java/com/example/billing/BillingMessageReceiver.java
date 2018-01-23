package com.example.billing;

import com.example.payments.Gateway;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.handler.annotation.Payload;

public class BillingMessageReceiver {
    private Gateway paymentGateway;
    private org.slf4j.Logger logger = LoggerFactory.getLogger(getClass());

    @Value("${queueName}")
    private String queueName;

    public BillingMessageReceiver(Gateway paymentGateway) {

        this.paymentGateway = paymentGateway;
    }

    @RabbitListener(queues = "${queueName}")
    public void process(@Payload BillingMessage message) {
        int amount = message.getAmount();
        if (paymentGateway.createReocurringPayment(amount)){
            logger.info("Received message with sucess");
        } else {
            logger.info("Failed Message");
        }

    }
}
