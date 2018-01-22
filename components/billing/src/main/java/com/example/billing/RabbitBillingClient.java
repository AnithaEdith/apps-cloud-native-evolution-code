package com.example.billing;

import org.springframework.amqp.rabbit.core.RabbitTemplate;

public class RabbitBillingClient implements BillingClient {
    private final String queueName;
    private final RabbitTemplate rabbitTemplate;
    String billingEndpoint;

    public RabbitBillingClient(String queueName, RabbitTemplate rabbitTemplate) {
        this.queueName = queueName;
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public void billUser(String userId, int paymentMonthlyAmount) {
        BillingMessage billingMessage=new BillingMessage(userId, paymentMonthlyAmount);
        rabbitTemplate.convertAndSend(queueName, billingMessage);
    }
}
