package com.upb.agripos;

import com.upb.agripos.model.Produk; // Import class Produk
import com.upb.agripos.util.CreditBy; // Import class CreditBy

public class MainProduk {
    public static void main(String[] args) {
        
        // Instansiasi tiga objek produk (p1, p2, p3)
        Produk p1 = new Produk("BNH-001", "Benih Padi IR64", 25000, 100);
        Produk p2 = new Produk("PPK-101", "Pupuk Urea 50kg", 350000, 40);
        Produk p3 = new Produk("ALT-501", "Cangkul Baja", 90000, 15);

        System.out.println("--- Daftar Produk Pertanian ---");
        System.out.println("Kode: " + p1.getKode() + ", Nama: " + p1.getNama() + ", Harga: " + p1.getHarga() + ", Stok: " + p1.getStok());
        System.out.println("Kode: " + p2.getKode() + ", Nama: " + p2.getNama() + ", Harga: " + p2.getHarga() + ", Stok: " + p2.getStok());
        System.out.println("Kode: " + p3.getKode() + ", Nama: " + p3.getNama() + ", Harga: " + p3.getHarga() + ", Stok: " + p3.getStok());


    System.out.println(); // 
        System.out.println( "Stok awal Benih Padi IR64: " + p1.getStok());
        p1.kurangiStok(27);
        System.out.println("sisa stok " + p1.getNama() + " setelah berkurang: " + p1.getStok()); 
    System.out.println(); // 
        System.out.println("Stok awal Pupuk Urea: " + p2.getStok());
        p2.tambahStok(42); 
        System.out.println("sisa stok " + p2.getNama() + " setelah bertambah: " + p2.getStok()); 
    System.out.println(); // 
        System.out.println("Stok awal Cangkul Baja: " + p3.getStok());
        p3.kurangiStok(100);

        // Panggil method CreditBy untuk menampilkan identitas

        CreditBy.print("240202828", "Akhmad akbar syarifudin"); 
    }
}