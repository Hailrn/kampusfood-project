package com.kampusfood.notification.channel;

// ConcreteImplementor
public class PushChannel implements NotificationChannel {
    @Override
    public void send(String recipient, String subject, String body) {
        System.out.printf("[Push Notification -> %s] (%s) %s%n", recipient, subject, body);
    }
}
