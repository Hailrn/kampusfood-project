package com.kampusfood.payment.external;

// Adaptee
public class NewPayService {
    public boolean pay(String userId, double amount, String currency) {
        System.out.println("[NewPay] Memproses pembayaran user: " + userId + " sebesar " + amount + " " + currency);
        return true;
    }
}
