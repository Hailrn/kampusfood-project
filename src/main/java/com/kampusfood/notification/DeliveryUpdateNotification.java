package com.kampusfood.notification;

import com.kampusfood.notification.channel.NotificationChannel;

// RefinedAbstraction
public class DeliveryUpdateNotification extends Notification {
    private final String orderId;
    private final String status;

    public DeliveryUpdateNotification(NotificationChannel channel, String orderId, String status) {
        super(channel);
        this.orderId = orderId;
        this.status = status;
    }

    @Override
    public void notify(String recipient) {
        String subject = "Update Pengiriman #" + orderId;
        String body = String.format("Status pengiriman pesanan #%s: %s.", orderId, status);
        channel.send(recipient, subject, body);
    }
}
