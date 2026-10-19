package com.kampusfood.notification;

import com.kampusfood.notification.channel.NotificationChannel;

// RefinedAbstraction
public class OrderConfirmationNotification extends Notification {
    private final String orderId;
    private final double total;

    public OrderConfirmationNotification(NotificationChannel channel, String orderId, double total) {
        super(channel);
        this.orderId = orderId;
        this.total = total;
    }

    @Override
    public void notify(String recipient) {
        String subject = "Konfirmasi Pesanan #" + orderId;
        String body = String.format("Pesanan Anda #%s telah dikonfirmasi dengan total pembayaran Rp %,.2f.", orderId, total);
        channel.send(recipient, subject, body);
    }
}
