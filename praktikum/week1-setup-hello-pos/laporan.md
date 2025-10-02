# Laporan Praktikum Minggu 1 (sesuaikan minggu ke berapa?)
Topik: [pembuatan program "Hello World, I am [nama]-[nim]" dalam 3 paradigma dalam java]

## Identitas
- Nama  : AKHMAD AKBAR SYARIFUDIN
- NIM   : 240202828
- Kelas : 3IKRA

---

## Tujuan
a. memahami perbedaan gaya pemrograman prosedural, OOP, dan fungsional.
b. mengimplementasikan program sederhana "Hello World, I am [nama]-[nim]" menggunakan ketiga gaya pemrograman.
c. membandingkan karakteristik dan pendekatan masing-masing gaya dalam menyelesaikan masalah yang sama.

---

## Dasar Teori
1. Pemrograman Prosedural adalah cara membuat program dengan menulis perintah-perintah yang dijalankan berurutan dari atas ke bawah, seperti mengikuti resep masakan langkah demi langkah.
2. Pemrograman OOP (Object-Oriented Programming) adalah cara membuat program dengan membuat objek-objek yang memiliki data dan fungsi sendiri. Class adalah cetakan/blueprint untuk membuat objek, seperti cetakan kue untuk membuat kue.
3. Pemrograman Fungsional adalah cara membuat program dengan fokus pada fungsi-fungsi yang mengolah data tanpa mengubah data aslinya. Menggunakan lambda expression (fungsi singkat) untuk menulis kode lebih ringkas.
4. Lambda Expression adalah cara singkat menulis fungsi dalam Java dengan format (parameter) -> hasil, sehingga tidak perlu membuat class atau method panjang.
5. Perbedaan ketiga paradigma: Prosedural menulis perintah step by step, OOP membuat objek dengan data dan fungsinya, sedangkan Fungsional fokus mengolah data dengan fungsi-fungsi pendek dan ringkas.

---

## Langkah Praktikum

1. Setup Environment
a. Memastikan JDK terinstal (Java Development Kit)
b. Menyiapkan text editor atau IDE (seperti VS Code, IntelliJ IDEA, atau Eclipse)
c. Membuat direktori kerja untuk menyimpan file Java

2. Implementasi Paradigma Prosedural

a. Membuat file HelloProsedural.java
b. Menulis kode dengan pendekatan prosedural menggunakan method main() langsung
c. Mendeklarasikan variabel nama dan NIM
d. Mencetak output menggunakan System.out.println()

3. Implementasi Paradigma OOP
a. Membuat file HelloOOP.java
b. Membuat class Mahasiswa dengan atribut nama dan nim
c. Membuat constructor untuk inisialisasi objek
d. Membuat method sapa() untuk menampilkan pesan
e. Membuat objek Mahasiswa dan memanggil method sapa()

4. Implementasi Paradigma Fungsional
a. Membuat file HelloFunctional.java
b. Mengimport java.util.function.BiConsumer
c. Menggunakan lambda expression untuk membuat fungsi sapa
d. Memanggil fungsi menggunakan method accept()

5. Kompilasi dan Eksekusi

a. Mengkompilasi setiap file: javac NamaFile.java
b. Menjalankan program: java NamaClass
c. Mendokumentasikan hasil output dari ketiga program


---

## Kode Program
 

```java
// helloprosedural 

public class HelloProsedural {

    public static void main(String[] args) {
        String nama = "AKHMAD AKBAR SYARIFUDIN";
        String NIM = "240202828";

        System.out.println("Hello world, i am  "+ nama+ " -" + NIM);
    }

}
```

```java
// hellooop.java
class Mahasiswa {
    String nama; int nim ; 
    Mahasiswa(String n, int u){ nama=n; nim =u; } 
    void sapa(){ System.out.println("Halo world i am, " + nama + ", "+nim ); } 
}

public class HelloOOP {
   public static void main(String[] args) {
      Mahasiswa m = new Mahasiswa("AKHMAD AKBAR SYARIFUDIN", 240202828); 
      m.sapa(); 
   }
}
```

```java
// hellofungsional
import java.util.function.BiConsumer;
 public class HelloFunctional
  { public static void main(String[] args) 
    { BiConsumer<String,Integer> sapa = (nama, nim) -> 
     System.out.println("Halo world, i am, " + nama + " - " + nim);
     sapa.accept("AKHMAD AKBAR SYARIFUDIN", 240202828); 
    } 
} 
```

---

## Hasil Eksekusi

hasilhelloprosedural.java.png
hasilhellooop.java.png
hasilhellofungtional.java.png

---

## Analisis
a. Prosedural yaitu Program berjalan secara linear atau urutan dari atas ke bawah. Variabel dideklarasikan di dalam method main(), kemudian langsung dicetak. 
b. OOP yaitu Program membuat blueprint (class Mahasiswa) yang memiliki atribut dan behavior. Objek m dibuat dari class tersebut dengan data spesifik, kemudian method sapa() dipanggil untuk menampilkan informasi. Pendekatan ini memisahkan data dan behavior dalam struktur yang terorganisir.
c. Functional yaitu Program menggunakan interface BiConsumer yang menerima dua parameter (String dan Integer). Lambda expression (nama, nim) -> ... mendefinisikan fungsi secara ringkas tanpa perlu membuat class terpisah. Fungsi dipanggil menggunakan method accept().

---

## Kesimpulan

a. Ketiga model pemrograman (prosedural, OOP, dan fungsional) dapat menyelesaikan masalah yang sama dengan pendekatan berbeda.
b. prosedural paling sederhana dan cocok untuk program kecil yang tidak memerlukan struktur kompleks.
c. OOP memberikan struktur yang lebih baik untuk aplikasi besar dengan enkapsulasi data dan behavior dalam objek, memudahkan maintenance dan pengembangan.
d. fungsional menunjukan cara yang lebih ringkas dan deklaratif, sangat berguna untuk operasi data dan menghindari side effects.
e. Pemilihan model pemrograman harus disesuaikan dengan kebutuhan proyek, skala aplikasi, dan tim pengembang. Tidak ada model pemrograman yang lebih baik; semuanya memiliki kelebihan dan kekurangan masing-masing.


---

## Quiz
1. Apakah OOP selalu lebih baik dari prosedural?
   **Jawaban:** Tidak selalu. OOP lebih baik untuk aplikasi besar dan kompleks karena lebih mudah dikembangkan dan dipelihara. Namun untuk program sederhana atau script kecil, prosedural justru lebih efisien dan mudah dipahami. Pemilihan paradigma tergantung pada skala dan kebutuhan proyek.

2. Kapan functional programming lebih cocok digunakan dibanding OOP atau prosedural?
   **Jawaban:** Functional programming lebih cocok digunakan ketika kita perlu memproses data dalam jumlah besar dan melakukan transformasi data. Paradigma ini sangat efektif untuk operasi pada collection seperti filter, map, dan reduce yang membuat kode lebih ringkas dan mudah dibaca. Functional programming juga membantu menghindari bug yang disebabkan oleh perubahan data yang tidak terduga karena sifatnya yang immutable. Selain itu, paradigma ini lebih aman untuk pemrograman paralel atau concurrent karena tidak mengubah state secara langsung sehingga mengurangi risiko konflik antar proses.

3. Bagaimana paradigma (prosedural, OOP, fungsional) memengaruhi maintainability dan scalability aplikasi?
   **Jawaban:** 
a. Prosedural: Maintainability rendah untuk aplikasi besar karena kode sulit diorganisir. Scalability terbatas.
b. OOP: Maintainability tinggi karena kode terstruktur dalam class dan object. Scalability baik untuk aplikasi kompleks dengan banyak fitur.
c. Fungsional: Maintainability baik karena fungsi bersifat independen dan reusable. Scalability baik untuk operasi data paralel.

4. Mengapa OOP lebih cocok untuk mengembangkan aplikasi POS dibanding prosedural?
   **Jawaban:** Aplikasi POS memiliki banyak entitas seperti Produk, Pelanggan, Transaksi, dan Kasir dengan data dan perilaku masing-masing. OOP memungkinkan setiap entitas direpresentasikan sebagai class yang jelas sehingga mudah menambah fitur baru tanpa mengubah kode lama. Kode menjadi lebih terorganisir dan mudah dipahami tim, enkapsulasi melindungi data sensitif seperti harga dan stok, serta reusability code lebih tinggi karena class dapat digunakan kembali di berbagai bagian aplikasi.

5. Bagaimana paradigma fungsional dapat membantu mengurangi kode berulang (*boilerplate code*)?
   **Jawaban:** Paradigma fungsional mengurangi boilerplate code dengan menggunakan lambda expression yang lebih ringkas dibanding anonymous class, serta higher-order functions yang dapat menerima fungsi sebagai parameter. Method reference juga membantu menghindari penulisan kode redundan, dan Stream API menggantikan loop berulang seperti for dan while. Contoh: list.stream().filter(x -> x > 5) jauh lebih singkat dan mudah dibaca dibanding harus menulis loop manual dengan if secara berulang-ulang.
