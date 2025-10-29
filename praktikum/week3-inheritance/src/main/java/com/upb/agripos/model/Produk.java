package main.java.com.upb.agripos.model;


public class Produk {
    @SuppressWarnings("FieldMayBeFinal")
    private String kode;
    private final String nama;
    private final double harga;
    private final int stok;

    public Produk(String kode, String nama, double harga, int stok) {
        this.kode = kode;
        this.nama = nama;
        this.harga = harga;
        this.stok = stok;
    }


    public String getKode() { return kode; }
    public String getNama() { return nama; }
    public double getHarga() { return harga; }
    public int getStok() { return stok; }
}