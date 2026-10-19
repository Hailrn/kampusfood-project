package com.kampusfood.payment.adapter;

import com.kampusfood.payment.PaymentMethod;
import com.kampusfood.payment.legacy.OldPayGateway;

// Adapter
public class OldPayGatewayAdapter implements PaymentMethod {
    private final OldPayGateway oldPayGateway;

    public OldPayGatewayAdapter(OldPayGateway oldPayGateway) {
        this.oldPayGateway = oldPayGateway;
    }

    @Override
    public boolean pay(String userId, double amount, String currency) {
        int cents = (int) Math.round(amount * 100);
        String account = "ACC-" + userId;
        return oldPayGateway.makePayment(account, cents);
    }
}
