# Laporan Praktikum Minggu 2
Topik: [Class dan Object (Produk Pertanian)]

## Identitas
- Nama  : Akhmad Akbar Syarifudin
- NIM   : 240202828
- Kelas : 3IKRA

---

## Tujuan
1. Mahasiswa mampu menjelaskan konsep class, object, atribut, dan method dalam OOP.

2. Mahasiswa mampu menerapkan access modifier dan enkapsulasi dalam pembuatan class.

3. Mahasiswa mampu mengimplementasikan class Produk pertanian dengan atribut dan method yang sesuai.

4. Mahasiswa mampu mendemonstrasikan instansiasi object serta menampilkan data produk pertanian di console.
---

## Dasar Teori
1. Class dan Object: Class adalah blueprint atau cetak biru dari sebuah object; object adalah instansiasi dari class yang berisi data dan perilaku.

2. Enkapsulasi: Prinsip OOP untuk menyembunyikan data dengan menjadikan atribut private, memastikan data hanya diakses melalui method getter dan setter.

3. Pemisahan Tanggung Jawab: Logika bisnis utama (Produk) dipisahkan dari fungsi pendukung (CreditBy di package util), menjaga modularitas kode.

---

## Langkah Praktikum
a. Setup Struktur: Membuat package com.upb.agripos.model dan com.upb.agripos.util.

b. Class Produk: Membuat Produk.java dengan atribut private dan method kurangiStok yang memiliki logika pencegahan stok negatif.

c. Class CreditBy: Membuat CreditBy.java di package util dengan method static print().

d. Instansiasi & Pengujian: Membuat MainProduk.java untuk menginstansiasi tiga object dan melakukan pengujian method stok: p1.kurangiStok(27), p2.tambahStok(42), dan p3.kurangiStok(100).

e. Kerapian Output: Menambahkan System.out.println(); di MainProduk.java untuk membuat jarak (spacing) yang jelas antara segmen output.


---

## Kode Program

```java
// Produk
package com.upb.agripos.model;

public class Produk {
    private String kode;
    private String nama;
    private double harga;
    private int stok;

    public Produk(String kode, String nama, double harga, int stok) {
        this.kode = kode;
        this.nama = nama;
        this.harga = harga;
        this.stok = stok;
    }

    public String getKode() { return kode; }
    public void setKode(String kode) { this.kode = kode; }

    public String getNama() { return nama; }
    public void setNama(String nama) { this.nama = nama; }

    public double getHarga() { return harga; }
    public void setHarga(double harga) { this.harga = harga; }

    public int getStok() { return stok; }
    public void setStok(int stok) { this.stok = stok; }
    

    public void tambahStok(int jumlah) {
        this.stok += jumlah;
    }

    public void kurangiStok(int jumlah) {
        if (this.stok >= jumlah) {
            this.stok -= jumlah;
        } else {
            System.out.println("Stok tidak mencukupi!");
        }
    }
}

```

```java
//CreditBy
package com.upb.agripos.util;

public class CreditBy {

    // Method statis, dapat dipanggil tanpa instansiasi objek
    public static void print(String nim, String nama) {
        System.out.println("\ncredit by: " + nim + " - " + nama);
    }
}

```java
//MainProduk
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

```

---
## Hasil Eksekusi

![Hasil Eksekusi Program Berhasil](screenshots/Screenshot 2025-10-07 231416.jpg)
---

## Analisis

- Pengurangan Berhasil (p1): Stok 100 dikurangi 27 menjadi 73. Logika if di kurangiStok terpenuhi. Penambahan     Berhasil (p2): Stok 40 ditambah 42 menjadi 82. Pencegahan Minus (p3): Ketika p3.kurangiStok(100) dipanggil (stok 15), kondisi if (15≥100) gagal. Program menjalankan blok else, mencetak "Stok tidak mencukupi!". Nilai stok p3 tetap 15, membuktikan penerapan enkapsulasi yang berhasil menjaga integritas data dari nilai negatif. Kode juga menunjukkan pemisahan tugas yang baik, dengan CreditBy sebagai utility terpisah yang dipanggil di akhir. 

- Apa perbedaan pendekatan minggu ini dibanding minggu sebelumnya: Perbedaan pendekatan minggu ini adalah beralih dari pendekatan prosedural (Minggu 1) menjadi Object-Oriented Programming (OOP). Minggu ini, data (stok, nama) dan logikanya (kurangiStok, tambahStok) disatukan ke dalam sebuah Class (Produk) dan diakses melalui Object (p1, p2). Pendekatan ini memungkinkan Enkapsulasi dan modularitas kode, yang tidak ada jika kode ditulis secara prosedural hanya di dalam method main seperti minggu sebelumnya.

- Kendala yang dihadapi yaitu  error "Unresolved compilation problems" dan typo pada package (until seharusnya util), yang diatasi melalui verifikasi struktur dan perintah Java: Clean Language Server Workspace untuk membersihkan cache IDE.
---

## Kesimpulan
Praktikum week 2 ini berhasil mengimplementasikan class Produk dengan konsep Enkapsulasi menggunakan atribut private dan method getter/setter. Logika if-else dalam method kurangiStok berfungsi sebagai validasi data, memastikan atribut stok tetap konsisten dan valid (tidak negatif), yang merupakan aspek penting dari data integrity dalam sistem POS.

---

## Quiz
1. Mengapa atribut sebaiknya dideklarasikan sebagai private dalam class?
Jawaban: Atribut dideklarasikan sebagai private untuk menerapkan prinsip Enkapsulasi. Tujuannya adalah mencegah data diubah secara langsung dari luar class (direct access), sehingga modifikasi hanya dapat dilakukan melalui setter yang sudah tervalidasi, menjaga keamanan data.

2. Apa fungsi getter dan setter dalam enkapsulasi?
Jawaban: Getter berfungsi untuk mengambil (membaca) nilai dari atribut private. Setter berfungsi untuk mengubah (menulis) nilai ke atribut private. Keduanya menyediakan interface publik yang terkontrol untuk berinteraksi dengan data internal class.

3. Bagaimana cara class Produk mendukung pengembangan aplikasi POS yang lebih kompleks?
Jawaban: Class Produk berfungsi sebagai Model Data terpusat, mendefinisikan struktur dan perilaku semua produk secara konsisten. Semua modul aplikasi POS (penjualan, inventaris, pelaporan) akan berinteraksi dengan object Produk yang memiliki aturan validasi terpusat (seperti pencegahan stok minus), membuat kode lebih terstruktur dan mudah di-maintain.

