package com.kampusfood.notification.channel;

/**
 * Interface implementor kanal notifikasi (Bridge pattern).
 */
// Implementor
public interface NotificationChannel {
    void send(String recipient, String subject, String body);
}
