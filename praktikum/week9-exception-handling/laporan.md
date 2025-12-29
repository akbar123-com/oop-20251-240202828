# Laporan Praktikum week 9

Topik: [Exception Handling, Custom Exception, dan Design Pattern Sederhana]

## Identitas
- Nama  : [ Akhmad Akbar Syarifudin]
- NIM   : [240202828]
- Kelas : [3ikra]

---

## Tujuan
Setelah menyelesaikan modul ini, diharapkan praktikan dapat:

1. Memahami perbedaan fundamental antara terminologi error dan exception dalam pemrograman.

2. Menguasai penggunaan struktur kontrol try–catch–finally secara efektif.

3. Merancang dan membuat custom exception untuk menangani logika bisnis yang spesifik.

4. Menerapkan mekanisme penanganan kesalahan ke dalam studi kasus nyata (sistem keranjang belanja).

5. Mengenal penerapan pola desain (Design Pattern) serta pengujian unit (Unit Testing) pada aplikasi.

---

## Dasar Teori
1. Error: Merupakan masalah serius yang muncul dari lingkungan eksekusi (JVM) yang umumnya tidak bisa dipulihkan oleh aplikasi itu sendiri.

2. Exception: Merupakan kondisi abnormal yang terjadi saat program berjalan, namun masih bisa diantisipasi dan ditangani agar program tidak berhenti secara mendadak.

3. Custom Exception: Kelas pengecualian yang dibuat sendiri oleh pengembang untuk merepresentasikan kesalahan yang berkaitan langsung dengan aturan bisnis aplikasi.

4. Blok Finally: Bagian kode yang memiliki jaminan untuk selalu dieksekusi, terlepas dari apakah pengecualian muncul atau berhasil ditangkap.

5. Manfaat: Penggunaan penanganan eksepsi yang baik akan meningkatkan ketahanan (robustness) serta mempermudah proses debugging.

---

## Langkah Praktikum
1. Mendefinisikan beberapa kelas custom exception baru, di antaranya: InvalidQuantityException, ProductNotFoundException, InsufficientStockException, CartEmptyException, dan DuplicateProductException.

2. Menyisipkan logika validasi pada metode-metode di dalam kelas ShoppingCart dengan memanfaatkan exception handling.

3. Membuat skenario pengujian pada kelas MainExceptionDemo untuk memicu setiap custom exception yang telah dibuat.

4. Menjalankan aplikasi dan melakukan verifikasi terhadap pesan kesalahan yang muncul di konsol.

5. Mengunggah hasil pekerjaan ke GitHub dengan pesan komit:
week9-exception: implement custom exception on shopping cart

---

## Kode Program 

```java
// CartEmptyException.java
package com.upb.agripos;

public class CartEmptyException extends Exception {
    public CartEmptyException(String msg) { super(msg); }
}
```

```java
// DuplicateProductException.java
package com.upb.agripos;

public class DuplicateProductException extends Exception {
    public DuplicateProductException(String msg) { super(msg); }
}

```

```java
// InsufficientStockException.java
package com.upb.agripos;

public class InsufficientStockException extends Exception {
    public InsufficientStockException(String msg) { super(msg); }
}

```

```java
// InvalidQuantityException.java
package com.upb.agripos;

public class InvalidQuantityException extends Exception {
    public InvalidQuantityException(String msg) { super(msg); }
}

```

```java
// MainExceptionDemo .java
package com.upb.agripos;

public class MainExceptionDemo {
    public static void main(String[] args) {
        System.out.println("Hello, I am akhmad akbar syarifudin (240202828) (Week9)");

        ShoppingCart cart = new ShoppingCart();
        Product p1 = new Product("P01", "Pupuk Organik", 25000, 3);

        // InvalidQuantityException
        try {
            cart.addProduct(p1, -1);
        } catch (Exception e) {
            System.out.println("Kesalahan: " + e.getMessage());
        }

        // ProductNotFoundException
        try {
            cart.removeProduct(p1);
        } catch (Exception e) {
            System.out.println("Kesalahan: " + e.getMessage());
        }

        // CartEmptyException
        try {
            cart.checkout();
        } catch (Exception e) {
            System.out.println("Kesalahan: " + e.getMessage());
        }

        // DuplicateProductException
        try {
            cart.addProduct(p1, 1);
            cart.addProduct(p1, 1); // duplikat
        } catch (Exception e) {
            System.out.println("Kesalahan: " + e.getMessage());
        }

        // InsufficientStockException
        try {
            ShoppingCart cart2 = new ShoppingCart();
            cart2.addProduct(p1, 5); // stok cuma 3
            cart2.checkout();
        } catch (Exception e) {
            System.out.println("Kesalahan: " + e.getMessage());
        }
    }
}

```

```java
// Product.java
package com.upb.agripos;

public class Product {
    private final String code;
    private final String name;
    private final double price;
    private int stock;

    public Product(String code, String name, double price, int stock) {
        this.code = code;
        this.name = name;
        this.price = price;
        this.stock = stock;
    }

    public String getCode() { return code; }
    public String getName() { return name; }
    public double getPrice() { return price; }
    public int getStock() { return stock; }
    public void reduceStock(int qty) { this.stock -= qty; }
}

```

```java
// ProductNotFoundException.java
package com.upb.agripos;

public class ProductNotFoundException extends Exception {
    public ProductNotFoundException(String msg) { super(msg); }
}

```

```java
// ShoppingCart.java
package com.upb.agripos;

import java.util.HashMap;
import java.util.Map;

public class ShoppingCart {
    private final Map<Product, Integer> items = new HashMap<>();

    public void addProduct(Product p, int qty)
            throws InvalidQuantityException, DuplicateProductException {

        if (qty <= 0) {
            throw new InvalidQuantityException(
                "Quantity harus lebih dari 0."
            );
        }

        if (items.containsKey(p)) {
            throw new DuplicateProductException(
                "Produk sudah ada di keranjang."
            );
        }

        items.put(p, qty);
    }

    public void removeProduct(Product p) throws ProductNotFoundException {
        if (!items.containsKey(p)) {
            throw new ProductNotFoundException("Produk tidak ada dalam keranjang.");
        }
        items.remove(p);
    }

    public void checkout()
            throws CartEmptyException, InsufficientStockException {

        if (items.isEmpty()) {
            throw new CartEmptyException(
                "Keranjang masih kosong."
            );
        }

        for (Map.Entry<Product, Integer> entry : items.entrySet()) {
            Product product = entry.getKey();
            int qty = entry.getValue();

            if (product.getStock() < qty) {
                throw new InsufficientStockException(
                    "Stok tidak cukup untuk: " + product.getName()
                );
            }
        }

        for (Map.Entry<Product, Integer> entry : items.entrySet()) {
            entry.getKey().reduceStock(entry.getValue());
        }
    }

    public double getTotalPrice() {
        double total = 0.0;
        for (Map.Entry<Product, Integer> entry : items.entrySet()) {
            total += entry.getKey().getPrice() * entry.getValue();
        }
        return total;
    }

    public void printCart() {
        System.out.println("Isi Keranjang (Map):");
        for (Map.Entry<Product, Integer> entry : items.entrySet()) {
            Product product = entry.getKey();
            int quantity = entry.getValue();
            System.out.println("- " + product.getCode() + " " + product.getName() + " = " + product.getPrice() + " x" + quantity);
        }
        System.out.println("Total: " + getTotalPrice());
    }
}

```

---

## Hasil Eksekusi
(Sertakan screenshot hasil eksekusi program.  
![hasilMainExceptionDemo](Screenshot 2025-12-30 000009)
)
---

## Analisis
1. Sistem saat ini telah dilengkapi dengan validasi berlapis untuk setiap tindakan pada fitur keranjang belanja.

2. Penggunaan custom exception memudahkan identifikasi masalah karena pesan yang muncul sangat relevan dengan alur bisnis (misalnya stok kurang atau keranjang kosong).

3. Perbedaan signifikan dibanding metode konvensional adalah kode menjadi lebih bersih; logika utama program tidak lagi tercampur baur dengan banyak pengecekan if-else untuk validasi kesalahan.

4. Tantangan teknis sempat muncul terkait checked exceptions, namun berhasil diatasi dengan penempatan blok catch atau deklarasi throws yang tepat.
---

## Kesimpulan
Penerapan exception handling dan pembuatan custom exception terbukti membuat struktur kode menjadi lebih profesional dan andal. Dengan pendekatan ini, aplikasi sistem POS yang dibangun menjadi lebih stabil karena setiap kegagalan proses dapat diinformasikan secara spesifik kepada pengguna.

---

## Quiz
1. Jelaskan perbedaan error dan exception. Jawaban: Error mengacu pada masalah berat pada sistem/hardware yang tidak disarankan untuk ditangkap oleh program (misalnya Out of Memory), sedangkan exception adalah masalah dalam logika program yang dapat diprediksi dan ditangani oleh programmer agar aplikasi tetap berjalan.

2. Apa fungsi finally dalam blok try–catch–finally? Jawaban: Fungsinya adalah untuk memastikan instruksi tertentu (seperti menutup koneksi database atau melepaskan resource) tetap dijalankan meskipun terjadi error maupun saat program berjalan normal.

3. Mengapa custom exception diperlukan? Jawaban: Agar developer dapat memberikan konteks kesalahan yang lebih bermakna dan spesifik sesuai dengan kebutuhan aplikasi, sehingga memudahkan pelacakan bug dan pengelolaan alur kerja aplikasi.

4. Berikan contoh kasus bisnis POS yang membutuhkan custom exception. Jawaban: Misalnya saat pelanggan mencoba melakukan pembayaran, namun saldo atau limit metode pembayaran tidak mencukupi, program dapat melempar InsufficientBalanceException
