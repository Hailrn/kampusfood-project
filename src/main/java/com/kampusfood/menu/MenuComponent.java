package com.kampusfood.menu;

/**
 * Interface komponen menu untuk hierarki menu (Composite pattern).
 */
// Component
public interface MenuComponent {
    String getName();
    double getPrice();
    void display(int depth);
}
