package com.kampusfood.model;

import java.util.Collections;
import java.util.List;

public class Order {
    private final String id;
    private final String userId;
    private final List<OrderLine> items;

    public Order(String id, String userId, List<OrderLine> items) {
        this.id = id;
        this.userId = userId;
        this.items = List.copyOf(items);
    }

    public String getId() {
        return id;
    }

    public String getUserId() {
        return userId;
    }

    public List<OrderLine> getItems() {
        return Collections.unmodifiableList(items);
    }

    public double total() {
        double sum = 0;
        for (OrderLine item : items) {
            sum += item.getSubtotal();
        }
        return sum;
    }
}
