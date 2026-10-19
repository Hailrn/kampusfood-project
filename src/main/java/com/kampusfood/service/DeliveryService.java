package com.kampusfood.service;

import com.kampusfood.model.Order;

public class DeliveryService {
    public String schedule(Order order) {
        String trackingCode = "DELIV-" + order.getId() + "-XYZ";
        System.out.println("[DeliveryService] Menjadwalkan pengiriman untuk Order ID: " + order.getId() + " | Kode: " + trackingCode);
        return trackingCode;
    }
}
