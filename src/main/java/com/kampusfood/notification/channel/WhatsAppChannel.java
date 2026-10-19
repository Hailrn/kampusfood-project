package com.kampusfood.notification.channel;

// ConcreteImplementor
public class WhatsAppChannel implements NotificationChannel {
    @Override
    public void send(String recipient, String subject, String body) {
        System.out.printf("[WhatsApp -> %s] *%s*%n%s%n", recipient, subject, body);
    }
}
