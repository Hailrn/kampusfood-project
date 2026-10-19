package com.kampusfood.service;

import com.kampusfood.model.Order;
import com.kampusfood.payment.PaymentMethod;

import java.util.HashMap;
import java.util.Map;

public class PaymentService {
    private final Map<String, PaymentMethod> registry = new HashMap<>();

    public void register(String type, PaymentMethod method) {
        registry.put(type, method);
    }

    public boolean charge(Order order, String type) {
        PaymentMethod method = registry.get(type);
        if (method == null) {
            System.out.println("[PaymentService] Tipe pembayaran tidak dikenal: " + type);
            return false;
        }
        System.out.println("[PaymentService] Memproses penagihan sebesar Rp " + order.total() + " dengan tipe: " + type);
        return method.pay(order.getUserId(), order.total(), "IDR");
    }
}
