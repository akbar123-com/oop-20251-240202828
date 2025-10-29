# Laporan Praktikum Minggu 1 
Topik: Inheritance (Pewarisan) dan Polymorphism (Bentuk Jamak)

## Identitas
- Nama  : akhmad akbar syarifudin
- NIM   : 240202828
- Kelas : 3IKRA

---

## Tujuan
Mahasiswa memahami konsep Inheritance (pewarisan sifat) dan Polymorphism (kemampuan objek untuk mengambil banyak bentuk), serta dapat mengimplementasikannya dalam kode Java, khususnya melalui teknik method overriding pada sistem Point of Sale (POS) untuk berbagai jenis produk pertanian (Benih, Pupuk, dll.).

---

## Dasar Teori
1. Inheritance: Mekanisme Java di mana sebuah class baru (subclass, cth: Benih) dibuat dari class yang sudah ada (superclass, cth: Produk) untuk mewarisi atribut dan metode, mempromosikan code reusability.

2. Polymorphism: Konsep yang memungkinkan sebuah variabel referensi (cth: Produk p = new Benih(...)) dapat merujuk pada objek dari subclass-nya. Hal ini memungkinkan metode yang sama memiliki perilaku berbeda tergantung tipe objek.

3. Method Overriding: Pendefinisian ulang sebuah metode yang sudah ada di superclass oleh subclass dengan signature (nama, parameter, tipe kembalian) yang sama persis. Anotasi @Override wajib digunakan.

4. super(): Kata kunci yang digunakan dalam konstruktor subclass untuk memanggil konstruktor dari superclass.

---

## Langkah Praktikum
1. Langkah-langkah yang dilakukan (setup, coding, run):

Setup Proyek: Memastikan file proyek terorganisir dalam package com.upb.agripos.model dan com.upb.agripos.

Implementasi Inheritance: Membuat kelas Benih, Pupuk, dan AlatPertanian mewarisi properti dari Produk menggunakan extends.

Implementasi Polymorphism: Menambahkan metode getInfo() di Produk.java dan meng-override-nya di setiap subclass (Benih.java, Pupuk.java, dll.) untuk menampilkan detail spesifik produk.

Eksekusi: Membuat objek dari berbagai subclass dan memanggil getInfo() melalui referensi superclass di MainPolymorphism.java.

2. File/kode yang dibuat: Produk.java, Benih.java, Pupuk.java, MainPolymorphism.java, dll.

3. Commit message yang digunakan: feat: implementasi inheritance dan polymorphism pada kelas produk turunan.

---

## Kode Program

```java
// AlatPertanian
package com.upb.agripos.model;

public class AlatPertanian extends Produk {
    private String bahan;

    public AlatPertanian(String kode, String nama, double harga, int stok, String bahan) {
        super(kode, nama, harga, stok);
        this.bahan = bahan;
    }

    @Override
    public String getInfo() {
        return "Alat Pertanian: " + super.getInfo() + ", Bahan: " + bahan;
    }
}
```

```java
//benih
package com.upb.agripos.model;

public class Benih extends Produk {
    private String varietas;

    public Benih(String kode, String nama, double harga, int stok, String varietas) {
        super(kode, nama, harga, stok);
        this.varietas = varietas;
    }

    @Override
    public String getInfo() {
        return "Benih: " + super.getInfo() + ", Varietas: " + varietas;
    }
}
```

```java
//ObatHama
package com.upb.agripos.model;

public class ObatHama extends Produk {
    private String bahanAktif;

    public ObatHama(String kode, String nama, double harga, int stok, String bahanAktif) {
        super(kode, nama, harga, stok);
        this.bahanAktif = bahanAktif;
    }

    @Override
public String getInfo() {
    return "Obat Hama: Produk: " + super.getInfo() + ", Bahan Aktif: " + bahanAktif;
}

}
```

```java
//Produk
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

    // === Method Overloading ===
    public void tambahStok(int jumlah) {
        this.stok += jumlah;
    }

    public void tambahStok(double jumlah) {
        this.stok += (int) jumlah;
    }

    // === Getter methods (tambahan penting) ===
    public String getKode() {
        return kode;
    }

    public String getNama() {
        return nama;
    }

    public double getHarga() {
        return harga;
    }

    public int getStok() {
        return stok;
    }

    // === Method Default (Overridable) ===
    public String getInfo() {
        return "Produk: " + nama + " (Kode: " + kode + ")";
    }

    public void kurangiStok(int i) {
         
        throw new UnsupportedOperationException("Unimplemented method 'kurangiStok'");
    }

    
}
```

```java
//pupuk
package com.upb.agripos.model;

public class Pupuk extends Produk {
    private String jenis;

    public Pupuk(String kode, String nama, double harga, int stok, String jenis) {
        super(kode, nama, harga, stok);
        this.jenis = jenis;
    }


    @Override
    public String getInfo() {
        return "Pupuk: " + super.getInfo() + ", Jenis: " + jenis;
    }

    public String getJenis() {
        return jenis;
    }
}
```

```java
//creditby
package com.upb.agripos.util;

public class CreditBy {
    public static void print() {
        System.out.println("\nCredit By: 240202828 - Akhmad Akbar syarifudin");
    }
}
```

```java
//MAINPOLYMORPHISM
package com.upb.agripos;

import com.upb.agripos.model.*;
import com.upb.agripos.util.CreditBy;

public class MainPolymorphism {
    public static void main(String[] args) {

        Produk[] daftarProduk = {
            new Benih("BNH-001", "Benih Padi IR64", 25000, 100, "IR64"),
            new Pupuk("PPK-101", "Pupuk Urea", 350000, 40, "Urea"),
            new AlatPertanian("ALT-501", "Cangkul Baja", 90000, 15, "Baja"),
            new ObatHama("ABADI-NANJAYA", "Obat pembasmi kutu daun", 100000, 20, "asamsulfate")
        };

        for (Produk p : daftarProduk) {
            System.out.println(p.getInfo());
        }

        CreditBy.print();
    }
}
```
---

## Hasil Eksekusi
(Screenshot 2025-10-29 165635)

---

## Analisis
1. Jelaskan bagaimana kode berjalan: Program dimulai di MainPolymorphism.java. Objek seperti Benih dibuat. Meskipun Benih adalah subclass, ia dapat menggunakan semua metode dari Produk (seperti getStok() atau kurangiStok()). Ketika metode getInfo() dipanggil, Java, melalui mekanisme dynamic binding (polymorphism), menentukan tipe objek yang sebenarnya (misalnya, Benih) dan menjalankan implementasi getInfo() yang paling spesifik di kelas Benih, yang mencakup detail tambahan seperti jenis benih.

2. Apa perbedaan pendekatan minggu ini dibanding minggu sebelumnya: Minggu sebelumnya berfokus pada enkapsulasi dan code reusability dalam satu kelas tunggal. Minggu ini, pendekatan bergeser ke hubungan antar kelas (Inheritance). Metode yang sama (getInfo()) dapat digunakan untuk berbagai subclass (Benih, Pupuk) namun menghasilkan output yang unik. Ini jauh lebih efisien daripada harus membuat metode terpisah seperti getBenihInfo() dan getPupukInfo().

3.  Kendala yang dihadapi dan cara mengatasinya: Kendala utama adalah error The method getInfo() of type Benih must override or implement a supertype method. Ini terjadi karena saya mencoba menggunakan @Override di Benih.java tanpa mendefinisikan metode getInfo() terlebih dahulu di superclass Produk.java. Kendala ini diatasi dengan menambahkan kerangka metode public void getInfo() di kelas Produk sehingga subclass dapat menimpa imple
---

## Kesimpulan
Praktikum ini berhasil mengimplementasikan Inheritance dan Polymorphism. Inheritance memastikan semua produk turunan (Benih, Pupuk, dll.) memiliki atribut dasar yang sama dari Produk. Polymorphism penting karena memungkinkan sistem POS memproses semua jenis produk melalui satu antarmuka (getInfo() atau hitungHarga()), tetapi setiap objek secara otomatis mengeksekusi logika yang spesifik untuk jenisnya (misalnya, menampilkan informasi khusus Benih atau Pupuk).

---

## Quiz

1. Overloading: Terjadi dalam satu kelas dengan metode bernama sama tetapi parameter berbeda. Diputuskan saat kompilasi (compile time).

Overriding: Terjadi antar kelas (Inheritance) dengan metode dan parameter yang sama persis (signature sama). Diputuskan saat program berjalan (runtime).

2. Bagaimana Java menentukan method mana yang dipanggil dalam dynamic binding? Jawaban: Java menentukan method mana yang dipanggil berdasarkan tipe objek yang sebenarnya (actual object type) pada saat runtime, bukan tipe referensi variabelnya. Jika method di-override, implementasi subclass yang paling spesifik akan selalu dieksekusi.

3. Berikan contoh kasus polymorphism dalam sistem POS selain produk pertanian. Jawaban: Contoh kasus Polymorphism pada skema Pengiriman Barang (Shipping):

Superclass: MetodePengiriman (memiliki metode hitungBiayaKirim()).

Subclass 1: PengirimanReguler (meng-override hitungBiayaKirim() dengan rumus biaya standar).

Subclass 2: PengirimanExpress (meng-override hitungBiayaKirim() dengan rumus biaya standar ditambah biaya percepatan).

Penerapan: Program kasir dapat memanggil pengiriman.hitungBiayaKirim() pada semua jenis objek pengiriman dan mendapatkan total biaya yang berbeda sesuai dengan tipe pengiriman yang dipilih.
