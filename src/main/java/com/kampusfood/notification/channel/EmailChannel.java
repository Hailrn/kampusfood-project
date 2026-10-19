package com.kampusfood.notification.channel;

// ConcreteImplementor
public class EmailChannel implements NotificationChannel {
    @Override
    public void send(String recipient, String subject, String body) {
        System.out.printf("[Email -> %s] Subjek: %s | Pesan: %s%n", recipient, subject, body);
    }
}
