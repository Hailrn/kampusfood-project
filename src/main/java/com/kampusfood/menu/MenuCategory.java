package com.kampusfood.menu;

// Composite
public class MenuCategory extends MenuComposite {
    public MenuCategory(String name) {
        super(name);
    }

    @Override
    public void display(int depth) {
        String indent = "  ".repeat(depth);
        System.out.printf("%s[Kategori] %s%n", indent, name);
        for (MenuComponent child : children) {
            child.display(depth + 1);
        }
    }
}
