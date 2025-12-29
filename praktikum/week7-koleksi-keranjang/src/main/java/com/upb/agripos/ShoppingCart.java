package com.upb.agripos;

import java.util.ArrayList;

public class ShoppingCart {
    private final ArrayList<Product> items = new ArrayList<>();

    public void addProduct(Product p) {
        items.add(p);
    }

    public void removeProduct(Product p) {
        items.remove(p);
    }

    public double getTotal() {
        double total = 0.0;
        for (Product p : items) {
            total += p.getPrice();
        }
        return total;
    }

    public void printCart() {
        System.out.println("Isi Keranjang:");
        for (Product p : items) {
            System.out.println("- " + p.getCode() + " " + p.getName() + " = " + p.getPrice());
        }
        System.out.println("Total: " + getTotal());
    }
}
