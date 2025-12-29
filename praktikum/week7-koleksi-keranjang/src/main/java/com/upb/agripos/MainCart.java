package com.upb.agripos;

public class MainCart {
    public static void main(String[] args) {
        System.out.println("Hellowww, I am Akhmad Akbar Syarifudin-240202828 (pertemuan/week7)");

        Product p1 = new Product("P01", "jagung", 71000.0);
        Product p2 = new Product("P02", "asam karbonat", 34000.0);
        Product p3 = new Product("P03", "sulvur", 234000.0);

        ShoppingCart cart = new ShoppingCart();
        cart.addProduct(p1);
        cart.addProduct(p2);
        cart.addProduct(p3);

        cart.printCart();

        System.out.println("\nSetelah menghapus " + p1.getCode() + " " + p1.getName() + " dari keranjang:");
        cart.removeProduct(p1);
        cart.printCart();
    }
}
