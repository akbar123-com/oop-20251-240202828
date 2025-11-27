package com.upb.agripos.util;

public class CreditBy {
    public static void print(String nim, String nama) {
        System.out.println("\n--- Credit ---");
        System.out.println("Praktikum Week 5: Abstraction & Interface");
        System.out.println("NIM: " + nim);
        System.out.println("Nama: " + nama);
        System.out.println("--------------");
    }

    // Add no-arg overload to keep API consistent across weeks
    public static void print() {
        System.out.println("\nCredit By: 240202828 - Akhmad Akbar syarifudin");
    }
}
