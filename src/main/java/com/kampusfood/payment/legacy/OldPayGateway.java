package com.kampusfood.payment.legacy;

// Adaptee
public class OldPayGateway {
    public boolean makePayment(String account, int amountInCents) {
        System.out.println("[OldPay] Memproses pembayaran akun: " + account + " sebesar " + amountInCents + " sen");
        return true;
    }
}
