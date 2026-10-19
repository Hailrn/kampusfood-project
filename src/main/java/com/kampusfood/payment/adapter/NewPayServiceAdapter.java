package com.kampusfood.payment.adapter;

import com.kampusfood.payment.PaymentMethod;
import com.kampusfood.payment.external.NewPayService;

// Adapter
public class NewPayServiceAdapter implements PaymentMethod {
    private final NewPayService newPayService;

    public NewPayServiceAdapter(NewPayService newPayService) {
        this.newPayService = newPayService;
    }

    @Override
    public boolean pay(String userId, double amount, String currency) {
        return newPayService.pay(userId, amount, currency);
    }
}
