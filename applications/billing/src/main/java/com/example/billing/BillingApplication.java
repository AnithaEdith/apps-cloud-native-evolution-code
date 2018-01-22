package com.example.billing;

import com.rabbitmq.client.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.example.payments.Gateway;
import com.example.payments.RecurlyGateway;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.context.annotation.Bean;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import javax.validation.Valid;
import org.springframework.amqp.core.Queue;

@SpringBootApplication
@EnableDiscoveryClient
@EnableCircuitBreaker
public class BillingApplication {

	@Value("${queueName}")
	String queueName;

	private ConnectionFactory connectionFactory;

	public static void main(String[] args) {
		SpringApplication.run(BillingApplication.class, args);
	}

	@Bean
	Gateway paymentGateway() {
	return new RecurlyGateway();
	}

	@Bean
	BillingMessageReceiver receiver(Gateway paymentGateway) {
		return new BillingMessageReceiver(paymentGateway);
	}

	@Bean
	MessageListenerAdapter listenerAdapter(BillingMessageReceiver receiver) {
		return new MessageListenerAdapter(receiver, "process");
	}


	@Bean
	Queue queue() {
		return new Queue(queueName, false);
	}

}
