package com.upb.agripos.week3;

import com.upb.agripos.week3.model.*;
import com.upb.agripos.week3.util.CreditBy;

public class MainInheritance {
    public static void main(String[] args) {
        // Subclass
        Benih b = new Benih("BNH-001", "Benih Padi IR64", 25000, 100, "IR64");
        Pupuk p = new Pupuk("PPK-101", "Pupuk Urea", 350000, 40, "Urea");
        AlatPertanian a = new AlatPertanian("ALT-501", "Cangkul Baja", 90000, 15, "Baja");

    
        System.out.println("=========================================");
        System.out.println("HASIL LATIHAN MANDIRI (Method deskripsi())");
        System.out.println("=========================================");
        
        System.out.println("Benih: " + b.deskripsi());
        System.out.println("Pupuk: " + p.deskripsi());
        System.out.println("Alat Pertanian: " + a.deskripsi());
        
        System.out.println("\n--- Hasil Tugas Wajib Sebelumnya ---");
        // Output dari tugas wajib 
        System.out.println("Benih: " + b.getNama() + " Varietas: " + b.getVarietas());
        
        CreditBy.print("240202828", "AKHMAD AKBAR SYARIFUDIN"); 
    }
}
