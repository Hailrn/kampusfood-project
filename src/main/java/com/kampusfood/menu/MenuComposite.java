package com.kampusfood.menu;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// Composite
public abstract class MenuComposite implements MenuComponent {
    protected final String name;
    protected final List<MenuComponent> children = new ArrayList<>();

    public MenuComposite(String name) {
        this.name = name;
    }

    public void add(MenuComponent component) {
        children.add(component);
    }

    public void remove(MenuComponent component) {
        children.remove(component);
    }

    public List<MenuComponent> getChildren() {
        return Collections.unmodifiableList(children);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public double getPrice() {
        double sum = 0;
        for (MenuComponent child : children) {
            sum += child.getPrice();
        }
        return sum;
    }

    @Override
    public void display(int depth) {
        String indent = "  ".repeat(depth);
        System.out.printf("%s+ %s (Total: Rp %,.2f)%n", indent, name, getPrice());
        for (MenuComponent child : children) {
            child.display(depth + 1);
        }
    }
}
