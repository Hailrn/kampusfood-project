package com.kampusfood.notification;

import com.kampusfood.notification.channel.NotificationChannel;

// RefinedAbstraction
public class PromoNotification extends Notification {
    private final String promoTitle;

    public PromoNotification(NotificationChannel channel, String promoTitle) {
        super(channel);
        this.promoTitle = promoTitle;
    }

    @Override
    public void notify(String recipient) {
        String subject = "Promo Spesial KampusFood";
        String body = "Jangan lewatkan promo menarik hari ini: " + promoTitle;
        channel.send(recipient, subject, body);
    }
}
