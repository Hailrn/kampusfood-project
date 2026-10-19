package com.kampusfood.menu;

// Leaf
public class MenuItem implements MenuComponent {
    private final String name;
    private final double price;

    public MenuItem(String name, double price) {
        this.name = name;
        this.price = price;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public double getPrice() {
        return price;
    }

    @Override
    public void display(int depth) {
        String indent = "  ".repeat(depth);
        System.out.printf("%s- %s: Rp %,.2f%n", indent, name, price);
    }
}
