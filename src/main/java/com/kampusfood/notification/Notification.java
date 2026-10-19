package com.kampusfood.notification;

import com.kampusfood.notification.channel.NotificationChannel;

// Abstraction
public abstract class Notification {
    protected final NotificationChannel channel;

    public Notification(NotificationChannel channel) {
        this.channel = channel;
    }

    public abstract void notify(String recipient);
}
