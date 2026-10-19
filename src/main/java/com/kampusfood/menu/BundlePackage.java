package com.kampusfood.menu;

// Composite
public class BundlePackage extends MenuComposite {
    private final double discountRate;

    public BundlePackage(String name, double discountRate) {
        super(name);
        this.discountRate = discountRate;
    }

    public double getDiscountRate() {
        return discountRate;
    }

    public double getOriginalPrice() {
        return super.getPrice();
    }

    @Override
    public double getPrice() {
        return super.getPrice() * (1.0 - discountRate);
    }

    @Override
    public void display(int depth) {
        String indent = "  ".repeat(depth);
        double original = getOriginalPrice();
        double finalPrice = getPrice();
        double discountPercent = discountRate * 100.0;
        System.out.printf("%s[Paket Bundle] %s - Asli: Rp %,.2f, Diskon: %.0f%%, Akhir: Rp %,.2f%n",
                indent, name, original, discountPercent, finalPrice);
        for (MenuComponent child : children) {
            child.display(depth + 1);
        }
    }
}
