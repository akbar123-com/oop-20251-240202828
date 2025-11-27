package com.upb.agripos.week3.model;

public class Pupuk extends Produk {
    private String jenis;

    public Pupuk(String kode, String nama, double harga, int stok, String jenis) {
        super(kode, nama, harga, stok);
        this.jenis = jenis;
    }

    // tambahan
    public String deskripsi() {
        return "Jenis: Pupuk | Nama: " + getNama() + " | Tipe: " + jenis +
               " | Harga: " + getHarga() + " | Stok: " + getStok();
    }

    public String getJenis() { return jenis; }
    public void setJenis(String jenis) { this.jenis = jenis; }
}
