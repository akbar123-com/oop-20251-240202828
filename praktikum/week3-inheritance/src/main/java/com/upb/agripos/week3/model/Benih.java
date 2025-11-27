package com.upb.agripos.week3.model;

public class Benih extends Produk {
    private String varietas;

    public Benih(String kode, String nama, double harga, int stok, String varietas) {
        super(kode, nama, harga, stok);
        this.varietas = varietas;
    }

    // tambahan 
    public String deskripsi() {
        return "Jenis: Benih | Nama: " + getNama() + " | Varietas: " + varietas +
               " | Harga: " + getHarga() + " | Stok: " + getStok();
    }

    public String getVarietas() { return varietas; }
    public void setVarietas(String varietas) { this.varietas = varietas; }
}
