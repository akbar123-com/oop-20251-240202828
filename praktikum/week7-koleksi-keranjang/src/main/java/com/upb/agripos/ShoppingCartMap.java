package com.upb.agripos;

import java.util.HashMap;
import java.util.Map;

public class ShoppingCartMap {
    private final Map<Product, Integer> items = new HashMap<>();

    public void addProduct(Product product) {
        items.put(product, items.getOrDefault(product, 0) + 1);
    }

    public void removeProduct(Product product) {
        if (items.containsKey(product)) {
            int quantity = items.get(product);
            if (quantity > 1) {
                items.put(product, quantity - 1);
            } else {
                items.remove(product);
            }
        }
    }

    public double getTotalPrice() {
        double total = 0.0;
        for (Map.Entry<Product, Integer> entry : items.entrySet()) {
            total += entry.getKey().getPrice() * entry.getValue();
        }
        return total;
    }

    public void printCart() {
        System.out.println("Isi Keranjang (Map):");
        for (Map.Entry<Product, Integer> entry : items.entrySet()) {
            Product product = entry.getKey();
            int quantity = entry.getValue();
            System.out.println("- " + product.getCode() + " " + product.getName() + " = " + product.getPrice() + " x" + quantity);
        }
        System.out.println("Total: " + getTotalPrice());
    }
}
