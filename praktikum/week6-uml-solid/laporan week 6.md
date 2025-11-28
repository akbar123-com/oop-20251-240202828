# Laporan Praktikum Minggu 6

Topik: Desain Arsitektur Sistem Taniku menggunakan UML dan Prinsip SOLID

## Identitas

* Nama  : Akhmad Akbar Syarifudin
* NIM   : 240202828
* Kelas : 3IKRA

---

## Tujuan

- Mahasiswa memahami desain arsitektur sistem menggunakan UML.

- Mahasiswa memahami penerapan prinsip SOLID dalam desain perangkat lunak.

- Mahasiswa mampu menganalisis hubungan antara kebutuhan fungsional dan desain UML.

- Mahasiswa mampu mengembangkan argumentasi desain berdasarkan prinsip OOP & SOLID.

---

## Deskripsi Sistem taniku

Taniku adalah sistem Point of Sale (POS) untuk penjualan produk pertanian (benih, pupuk, alat).

Terdapat dua peran utama dalam sistem ini:

- Admin / Manajer → Mengelola Kelola Produk dan Lihat Laporan.

- Kasir → Melakukan Login, Proses Checkout, Bayar Tunai, Bayar E-Wallet, dan Cetak Struk.

Kebutuhan Fungsional (FR) yang dicakup meliputi Manajemen Produk (CRUD), Transaksi Penjualan (Checkout), Pembayaran Tunai & E-Wallet, Pencetakan Struk, serta Login & Hak Akses.

---

## 1. Desain Arsitektur UML

Empat diagram UML disusun untuk menggambarkan struktur dan perilaku sistem.

### 1.1 Use Case Diagram

Diagram ini menjelaskan fungsi sistem dari sudut pandang aktor.

- Kasir dapat melakukan Proses Checkout, yang mencakup (menggunakan include) Bayar Tunai dan Bayar E-Wallet.

- Proses Checkout juga mencakup Cetak Struk.

| Aktor | Use Case                            |
| ----- | ----------------------------------- |
| Admin/manajer |  Kelola Produk (gudang), Lihat Laporan  |
| Kasir | Login, Proses Checkout, Bayar Tunai, Bayar E-Wallet, Cetak Struk |

![Usecase Diagram](docs/usecase_uml.png)

### 1.2 Activity Diagram – Proses Checkout

Diagram ini menggambarkan alur kerja pembayaran, dibagi dalam Swimlanes Kasir (User) dan Sistem.

- Jalur Pembayaran diputuskan pada Decision Node Pilih Media? (cesh vs E-Many).

- Kedua jalur yang berhasil (cukup dan proses & Berhasil) akan menuju Transaksi dilakukan.

![Activity Diagram](docs/activity_uml.png)

### 1.3 Sequence Diagram – Proses Pembayaran

Diagram ini menjelaskan interaksi antar objek (kelas) selama pembayaran.

- CheckoutService memanggil PaymentFactory untuk createPayment(methodType).

- CheckoutService kemudian memanggil prosesPembayaran(amountReceived) pada objek pembayaran yang dihasilkan.

- Jika status BERHASIL, CheckoutService akan melakukan updateStokDB() dan cetakStruk().

![Sequence Diagram](docs/squence_uml.png)

### 1.4 Class Diagram

vDiagram ini menunjukkan struktur statis sistem.

- Cash dan EWallet mewarisi dari kelas induk Pembayaran.

- Terdapat Interface Receiptable (untuk cetakStruk()) dan Validatable (untuk validasi()).

![Class Diagram](docs/class_uml.png)

---

## 2. Penerapan Prinsip SOLID

Desain Taniku menerapkan prinsip SOLID untuk memastikan kemudahan pemeliharaan (maintainability) dan pengembangan (extensibility).

| Prinsip     | Penjelasan                                        | Penerapan                                                                                |
| ----------- | ------------------------------------------------- | ---------------------------------------------------------------------------------------- |
| **S (SRP)** | Sebuah kelas seharusnya hanya memiliki satu alasan untuk diubah atau dimodifikasi.                  | CheckoutService hanya bertanggung jawab atas alur transaksi, sementara tanggung jawab pengelolaan akses data berada sepenuhnya pada ProductRepository.                     |
| **O (OCP)** | Modul perangkat lunak harus terbuka untuk penambahan fitur, tetapi tertutup untuk perubahan pada kode intinya. | Untuk menambahkan jenis pembayaran baru (misalnya QrisPayment), pengembang hanya perlu membuat class implementasi baru. Inti dari CheckoutService (kode lama) tidak perlu disentuh sama sekali.                       |
| **L (LSP)** | Objek dari subclass harus dapat menggantikan objek dari superclass tanpa mengganggu fungsionalitas program.                | Implementasi Cash dan EWallet harus dapat disubstitusikan oleh CheckoutService melalui referensi kelas induk Pembayaran (atau interface-nya) tanpa menyebabkan error.                      |
| **I (ISP)** | Mengutamakan banyak interface yang kecil dan spesifik daripada satu interface yang besar dan umum.                    | Daripada membuat satu interface besar, sistem memisahkan tanggung jawab menjadi interface khusus seperti Receiptable (untuk cetak struk) dan Validatable (untuk otorisasi saldo).                |
| **D (DIP)** | Modul tingkat tinggi harus bergantung pada abstraksi, bukan pada detail implementasi tingkat rendah.              | CheckoutService (modul tingkat tinggi) berinteraksi dengan ProductRepository melalui interface (abstraksi), sehingga tidak terikat pada implementasi konkret tertentu, misalnya koneksi langsung ke database. |

---

## 3. Traceability Matrix (FR → Desain)

| FR                 | Use Case                | Activity/Sequence            | Realisasi                                                  |
| ------------------ | ----------------------- | ---------------------------- | ---------------------------------------------------------- |
| Manajemen Produk   | UC-Kelola Produk        | -                            | ProductService, ProductRepository, Product                 |
| Transaksi/Checkout | UC-Proses Checkout      | Activity Checkout            | CheckoutService, ProductService                            |
| Pembayaran         | UC-Bayar Tunai/E-Wallet | Sequence Pembayaran          | Pembayaran (class), Cash, EWallet, PaymentFactory |
| Cetak Struk        | UC-Cetak Struk          | Activity/Sequence Pembayaran | Receiptable (interface)                                                    |
| Login/Akses        | UC-Login                | -                            | AuthService, User                                          |

---

## 4. Quiz & Argumentasi Desain

### A. Perbedaan Aggregation dan Composition + Contoh

Hubungan antara objek dalam OOP dibedakan berdasarkan kekuatan ketergantungan. Aggregation adalah hubungan lemah (weak association) atau "Has-a", di mana objek part dapat tetap eksis meskipun objek whole (induk) dihancurkan. Contohnya adalah hubungan antara Store dan Cashier; Kasir masih memiliki eksistensi sebagai entitas meskipun Toko dihapus dari sistem. Sebaliknya, Composition adalah hubungan kuat (strong association); objek part akan lenyap jika objek whole hilang. Contoh tipikalnya adalah antara Transaction dan TransactionDetail, di mana detail transaksi tidak relevan lagi (hilang) jika transaksi induknya dihapus.

### B. Bagaimana OCP membuat sistem mudah dikembangkan?

Prinsip Open-Closed Principle (OCP) memastikan bahwa kode harus terbuka untuk ekstensi (penambahan fitur) tetapi tertutup untuk modifikasi pada kode yang sudah ada. Hal ini krusial untuk membuat sistem mudah dikembangkan, karena pengembang dapat menambahkan fungsionalitas baru (seperti metode pembayaran QRIS baru) hanya dengan membuat class implementasi baru, tanpa perlu mengubah kode lama pada CheckoutService. Dengan demikian, risiko timbulnya bug pada kode yang sudah stabil dapat diminimalisir.

### C. Mengapa DIP meningkatkan testability?

Dependency Inversion Principle (DIP) meningkatkan testability karena ia membalik ketergantungan; modul tingkat tinggi (seperti CheckoutService) dibuat bergantung pada abstraksi (interface), bukan pada detail implementasi konkret (modul tingkat rendah). Karena ketergantungan ini didasarkan pada interface, pengujian unit menjadi lebih mudah: pengembang dapat menggunakan objek Mock (tiruan) dari interface (misalnya, MockProductRepository) alih-alih menghubungkan kode ke database nyata. Hal ini membuat unit test berjalan lebih cepat, terisolasi, dan hasilnya lebih dapat diandalkan.

---

## Kesimpulan

Desain arsitektur sistem Taniku yang menggunakan UML dan prinsip SOLID:

- Memenuhi semua kebutuhan fungsional.

- Memiliki maintainability tinggi dan mudah diperluas (extensible).

- Meminimalkan ketergantungan langsung (loose coupling).

Penggunaan Factory Pattern dan Interface memastikan fleksibilitas tinggi dalam menangani berbagai metode pembayaran.

---
