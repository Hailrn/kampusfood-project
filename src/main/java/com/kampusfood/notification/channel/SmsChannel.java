package com.kampusfood.notification.channel;

// ConcreteImplementor
public class SmsChannel implements NotificationChannel {
    @Override
    public void send(String recipient, String subject, String body) {
        System.out.printf("[SMS -> %s] [%s] %s%n", recipient, subject, body);
    }
}
