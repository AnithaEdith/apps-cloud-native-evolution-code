package com.example.subscriptions;

//import com.example.billing.ChargeUser;
import com.example.billing.BillingClient;
import com.example.email.SendEmail;

public class CreateSubscription {

   // private final ChargeUser chargeUser;
    private final SendEmail emailSender;
    private final SubscriptionRepository subscriptions;
    private final BillingClient billingClient;

    public CreateSubscription(
            BillingClient billingClient,
     //       ChargeUser chargeUser,
            SendEmail emailSender, SubscriptionRepository subscriptions) {
       // this.chargeUser = chargeUser;
        this.billingClient=billingClient;
        this.emailSender = emailSender;
        this.subscriptions = subscriptions;
    }

    public void run(String userId, String packageId) {
        subscriptions.create(new Subscription(userId, packageId));
        billingClient.billUser("abc", 100);
        //chargeUser.run(userId, 100);
        emailSender.run("me@example.com", "Subscription Created", "Some email body");
    }
}
