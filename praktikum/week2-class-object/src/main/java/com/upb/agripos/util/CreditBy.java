package com.upb.agripos.util;

public class CreditBy {

    // Method statis, dapat dipanggil tanpa instansiasi objek
    public static void print(String nim, String nama) {
        System.out.println("\ncredit by: " + nim + " - " + nama);
    }

    // Add no-arg overload so editor and other weeks can call CreditBy.print()
    public static void print() {
        System.out.println("\nCredit By: 240202828 - Akhmad Akbar syarifudin");
    }
}
