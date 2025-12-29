# Laporan Praktikum Minggu 7
Topik: Koleksi Keranjang (ArrayList dan HashMap)

## Identitas
- Nama  : Akhmad Akbar Syarifudin
- NIM   : 240202828
- Kelas : [3IKRA]

---

## Tujuan
Mahasiswa memahami konsep koleksi dalam Java menggunakan ArrayList dan HashMap, serta dapat mengimplementasikan shopping cart dengan kedua struktur data tersebut.

---

## Dasar Teori
1. ArrayList adalah implementasi dari interface List yang menggunakan array dinamis untuk menyimpan elemen.
2. HashMap adalah implementasi dari interface Map yang menggunakan hash table untuk menyimpan pasangan key-value.
3. Koleksi di Java menyediakan cara yang lebih fleksibel dan efisien untuk mengelola data dibandingkan array biasa.
4. Exception handling penting untuk menangani error yang mungkin terjadi saat operasi pada koleksi.

---

## Langkah Praktikum
1. Membuat class Product dengan atribut code, name, dan price.
2. Membuat class ShoppingCart menggunakan ArrayList untuk menyimpan produk.
3. Membuat class ShoppingCartMap menggunakan HashMap untuk menyimpan produk dan quantity.
4. Membuat MainCart.java untuk mendemonstrasikan penggunaan ShoppingCart.
5. Membuat MainCartMap.java untuk mendemonstrasikan penggunaan ShoppingCartMap.
6. Mengalami error pada MainCart.java karena kode dalam format decompiled.
7. Berhasil menjalankan MainCartMap.java tanpa masalah.

---

## Kode Program
Kode utama untuk MainCartMap.java:

1. membuat class product

```java
// Product.java
package com.upb.agripos;

public class Product {
   private final String code;
   private final String name;
   private final double price;

   public Product(String var1, String var2, double var3) {
      this.code = var1;
      this.name = var2;
      this.price = var3;
   }

   public String getCode() {
      return this.code;
   }

   public String getName() {
      return this.name;
   }

   public double getPrice() {
      return this.price;
   }
}

```

2. membuat ShoppingCart

```java
// ShoppingCart.java
package com.upb.agripos;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Iterator;

public class ShoppingCart {
   private final ArrayList<Product> items = new ArrayList();

   public ShoppingCart() {
   }

   public void addProduct(Product var1) {
      this.items.add(var1);
   }

   public void removeProduct(Product var1) {
      this.items.remove(var1);
   }

   public double getTotal() {
      double var1 = 0.0;

      Product var4;
      for(Iterator var3 = this.items.iterator(); var3.hasNext(); var1 += var4.getPrice()) {
         var4 = (Product)var3.next();
      }

      return var1;
   }

   public void printCart() {
      System.out.println("Isi Keranjang:");
      Iterator var1 = this.items.iterator();

      while(var1.hasNext()) {
         Product var2 = (Product)var1.next();
         PrintStream var10000 = System.out;
         String var10001 = var2.getCode();
         var10000.println("- " + var10001 + " " + var2.getName() + " = " + var2.getPrice());
      }

      System.out.println("Total: " + this.getTotal());
   }
}


```

3. membuat ShoppingCartMap

```java
// ShoppingCartMap.java

package com.upb.agripos;

import java.util.HashMap;
import java.util.Map;

public class ShoppingCartMap {
    private final Map<Product, Integer> items = new HashMap<>();

    public void addProduct(Product product) {
        items.put(product, items.getOrDefault(product, 0) + 1);
    }

    public void removeProduct(Product product) {
        if (items.containsKey(product)) {
            int quantity = items.get(product);
            if (quantity > 1) {
                items.put(product, quantity - 1);
            } else {
                items.remove(product);
            }
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
4. program utama 

```java
// MainCartMap.java

package com.upb.agripos;

public class MainCartMap {
    public static void main(String[] args) {
        System.out.println("Hellowww, I am Akhmad Akbar Syarifudin-240202828 (pertemuan/week7)");

        Product p1 = new Product("P01", "jagung", 71000);
        Product p2 = new Product("P02", "asam karbonat", 34000);
        Product p3 = new Product("P03", "sulvur", 234000);

        ShoppingCartMap cart = new ShoppingCartMap();
        cart.addProduct(p1);
        cart.addProduct(p1); // tambah lagi untuk test quantity
        cart.addProduct(p2);
        cart.addProduct(p2);
        cart.addProduct(p3);

        cart.printCart();

        System.out.println("\nSetelah menghapus " + p1.getCode() + " " + p1.getName() + " dari keranjang:");
        cart.removeProduct(p1);
        cart.printCart();
    }
}

```
 

```java
// MainCart.java
package com.upb.agripos;

public class MainCart {
   public MainCart() {
   }

   public static void main(String[] var0) {
      System.out.println("Hellowww, I am Akhmad Akbar Syarifudin-240202828 (pertemuan/week7)");
      Product var1 = new Product("P01", "jagung", 71000);
      Product var2 = new Product("P02", "asam karbonat", 34000);
      Product var3 = new Product("P03", "sulvur", 234000);
      ShoppingCart var4 = new ShoppingCart();
      var4.addProduct(var1);
      var4.addProduct(var2);
      var4.addProduct(var3);
      var4.printCart();
      System.out.println("\nSetelah menghapus " + var1.getCode() + " " + var1.getName() + " dari keranjang:");
      var4.removeProduct(var1);
      var4.printCart();
   }
}

```

---

## Hasil Eksekusi

![HasilMainCartMap](Screenshot 2025-12-30 005713)

![HasilMainCart](Screenshot 2025-12-30 005838)
---

## Analisis
- MainCartMap.java menggunakan HashMap untuk menyimpan produk sebagai key dan quantity sebagai value, sehingga dapat menangani multiple quantity dari produk yang sama.

- MainCart.java menggunakan ArrayList sederhana, hanya menyimpan produk tanpa quantity tracking.

- Kendala utama adalah MainCart.java dalam format decompiled, yang membuat kode sulit dibaca dan tidak dapat langsung dijalankan tanpa modifikasi.

- Perbedaan dengan minggu sebelumnya: Minggu ini fokus pada penggunaan koleksi Java untuk mengelola data dinamis, sedangkan minggu sebelumnya lebih fokus pada inheritance dan polymorphism.

---

## Kesimpulan
Implementasi shopping cart menggunakan HashMap lebih powerful karena dapat tracking quantity, sedangkan ArrayList lebih sederhana. Kode decompiled dapat menyebabkan masalah readability dan eksekusi, sehingga perlu diperbaiki ke format standar.

---

## Quiz
1. Jelaskan perbedaan antara List, Map, dan Set. Jawaban: List adalah koleksi yang menyimpan elemen secara berurutan dan mengizinkan adanya data duplikat. Set berfungsi untuk menyimpan kumpulan data di mana setiap elemennya harus unik (tidak boleh ada yang sama). Sementara itu, Map digunakan untuk menyimpan data dalam bentuk pasangan kunci dan nilai (key-value pairs), di mana setiap kunci bersifat unik untuk mengakses nilainya.

2. Mengapa ArrayList cocok untuk keranjang belanja sederhana? Jawaban: ArrayList sangat ideal karena strukturnya yang dinamis memudahkan proses penambahan atau penghapusan barang. Selain itu, koleksi ini menjaga urutan barang sesuai waktu dimasukkan dan sangat efisien dalam penggunaan memori untuk menangani jumlah data yang relatif kecil pada aplikasi sederhana.

3. Bagaimana Set mencegah duplikasi data? Jawaban: Set menjamin integritas data dengan memanfaatkan mekanisme pengecekan melalui metode hashCode() dan equals(). Sebelum data baru disimpan, Set akan memverifikasi apakah objek tersebut sudah ada dalam koleksi; jika terdeteksi identik, maka data tersebut tidak akan ditambahkan.

4. Kapan sebaiknya menggunakan Map dibandingkan List? Berikan contoh. Jawaban: Map lebih tepat digunakan ketika kita perlu menghubungkan suatu entitas dengan data spesifik lainnya untuk mempercepat proses pencarian. Contohnya dalam sistem POS, kita menggunakan Map untuk memetakan ID Produk (sebagai key) dengan Jumlah/Kuantitas barang yang dibeli (sebagai value), sehingga kita bisa langsung memperbarui jumlah barang tanpa harus mencari di seluruh daftar.
