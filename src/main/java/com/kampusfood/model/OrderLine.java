package com.kampusfood.model;

import com.kampusfood.menu.MenuComponent;

public class OrderLine {
    private final MenuComponent item;
    private final int quantity;

    public OrderLine(MenuComponent item, int quantity) {
        this.item = item;
        this.quantity = quantity;
    }

    public MenuComponent getItem() {
        return item;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getSubtotal() {
        return item.getPrice() * quantity;
    }
}
