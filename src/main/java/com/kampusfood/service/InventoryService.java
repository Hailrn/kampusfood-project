package com.kampusfood.service;

import com.kampusfood.model.Order;

public class InventoryService {
    public boolean reserve(Order order) {
        System.out.println("[InventoryService] Memesan stok untuk Order ID: " + order.getId());
        return true;
    }

    public void release(Order order) {
        System.out.println("[InventoryService] Melepas stok untuk Order ID: " + order.getId());
    }
}
