package com.kampusfood.service;

import com.kampusfood.model.Order;
import com.kampusfood.notification.DeliveryUpdateNotification;
import com.kampusfood.notification.Notification;
import com.kampusfood.notification.OrderConfirmationNotification;
import com.kampusfood.notification.channel.NotificationChannel;

public class NotificationService {
    public void orderConfirmed(Order order, NotificationChannel channel) {
        Notification notification = new OrderConfirmationNotification(channel, order.getId(), order.total());
        notification.notify(order.getUserId());
    }

    public void deliveryUpdate(Order order, NotificationChannel channel) {
        Notification notification = new DeliveryUpdateNotification(channel, order.getId(), "Dalam Perjalanan ke Kampus");
        notification.notify(order.getUserId());
    }
}
