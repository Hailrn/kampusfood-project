package com.kampusfood.payment;

/**
 * Interface target pembayaran terpadu untuk semua metode pembayaran.
 */
// Target
public interface PaymentMethod {
    boolean pay(String userId, double amount, String currency);
}
