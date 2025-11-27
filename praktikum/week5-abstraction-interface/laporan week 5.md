# Laporan Praktikum Minggu 1 (sesuaikan minggu ke berapa?)
Topik: ["Abstraction (Abstract Class & Interface)"]

## Identitas
- Nama  : [AKHMAD AKBAR SYARIFUDIN]
- NIM   : [240202828]
- Kelas : [3IKRA]

---

## Tujuan
(Mahasiswa mampu mendemonstrasikan perancangan hierarki kelas menggunakan Abstract Class (Pembayaran) untuk shared state dan Interface (Validatable, Receiptable) untuk mendefinisikan kontrak kemampuan (perilaku), serta menerapkan Multiple Inheritance melalui interface.)

---

## Dasar Teori
(
1. Abstraksi: Proses menyederhanakan kompleksitas dengan menampilkan elemen esensial dan menyembunyikan detail implementasi.

2. Abstract Class: Digunakan untuk mendefinisikan kerangka dasar dan state yang akan diwariskan ("adalah sejenis dari").

3. Interface: Kumpulan kontrak (method tanpa implementasi konkrit) yang mendefinisikan kemampuan ("memiliki kemampuan").

4. Multiple Inheritance via Interface: Memungkinkan class mengimplementasikan banyak kontrak dari berbagai sumber, yang lebih aman di Java karena menghindari konflik state.)
---

## Langkah Praktikum
(
1. Abstract Class: Membuat Pembayaran.java dengan field invoiceNo, total, dan method abstrak biaya() serta prosesPembayaran().2.

2. Subclass Konkret: Membuat Cash.java (biaya $0.0$) dan EWallet.java (biaya $1.5\%$) yang mewarisi Pembayaran.

3. Interface: Membuat Validatable.java dan Receiptable.java.

4. Multiple Inheritance: Mengimplementasikan EWallet dengan dua interface (Validatable, Receiptable), dan Cash dengan Receiptable.

5. Main Class: Menjalankan demonstrasi polimorfisme dan credit by di MainAbstraction.java.)
---

## Kode Program
(Tuliskan kode utama yang dibuat, contoh:  

```java
// Receiptable.java
package com.upb.agripos.model.kontrak;

public interface Receiptable {
    String cetakStruk();
}
```

```java
// Validatable.java
package com.upb.agripos.model.kontrak;

public interface Validatable {
    boolean validasi();
}
```

```java
// Cash.java
package com.upb.agripos.model.pembayaran;

import com.upb.agripos.model.kontrak.Receiptable;

public class Cash extends Pembayaran implements Receiptable {
    private double tunai;

    public Cash(String invoiceNo, double total, double tunai) {
        super(invoiceNo, total);
        this.tunai = tunai;
    }

    @Override
    public double biaya() {
        return 0.0;
    }

    @Override
    public boolean prosesPembayaran() {
        return tunai >= totalBayar();
    }

    @Override
    public String cetakStruk() {
        return String.format("CASH | INVOICE %s | TOTAL: %.2f | BAYAR CASH: %.2f | KEMBALI: %.2f",
                invoiceNo, totalBayar(), tunai, Math.max(0, tunai - totalBayar()));
    }
}
```

```java
// EWallet.java
package com.upb.agripos.model.pembayaran;

import com.upb.agripos.model.kontrak.Validatable;
import com.upb.agripos.model.kontrak.Receiptable;

public class EWallet extends Pembayaran implements Validatable, Receiptable {
    private String akun;
    private String otp;

    public EWallet(String invoiceNo, double total, String akun, String otp) {
        super(invoiceNo, total);
        this.akun = akun;
        this.otp = otp;
    }

    @Override
    public double biaya() {
        return total * 0.015;
    }

    @Override
    public boolean validasi() {
        return otp != null && otp.length() == 6;
    }

    @Override
    public boolean prosesPembayaran() {
        return validasi();
    }

    @Override
    public String cetakStruk() {
        return String.format("E-WALLET | INVOICE %s | TOTAL+FEE: %.2f | AKUN: %s | STATUS: %s | OTP Valid: %s",
                invoiceNo, totalBayar(), akun, prosesPembayaran() ? "BERHASIL" : "GAGAL", validasi());
    }
}
```

```java
// Pembayaran.java
package com.upb.agripos.model.pembayaran;

public abstract class Pembayaran {
    protected String invoiceNo;
    protected double total;

    public Pembayaran(String invoiceNo, double total) {
        this.invoiceNo = invoiceNo;
        this.total = total;
    }

    public abstract double biaya();
    public abstract boolean prosesPembayaran();

    public double totalBayar() {
        return total + biaya();
    }

    public String getInvoiceNo() { return invoiceNo; }
    public double getTotal() { return total; }
}
```
```java
// CreditBy.java
package com.upb.agripos.util;

public class CreditBy {
    public static void print(String nim, String nama) {
        System.out.println("\n--- Credit ---");
        System.out.println("Praktikum Week 5: Abstraction & Interface");
        System.out.println("NIM: " + nim);
        System.out.println("Nama: " + nama);
        System.out.println("--------------");
    }

    // Add no-arg overload to keep API consistent across weeks
    public static void print() {
        System.out.println("\nCredit By: 240202828 - Akhmad Akbar syarifudin");
    }
}

```java
// MainAbstraction.java
package com.upb.agripos;

import com.upb.agripos.model.kontrak.*;
import com.upb.agripos.model.pembayaran.*;
import com.upb.agripos.util.CreditBy;

public class MainAbstraction {
    public static void main(String[] args) {
        // Kasus 1: Pembayaran Tunai (Cash) - Uang Cukup
        Pembayaran cashOK = new Cash("TRO-023", 145700, 150000);
        
        // Kasus 2: Pembayaran E-Wallet - Validasi Gagal (OTP kurang dari 6 digit)
        Pembayaran ewGagal = new EWallet("IRB-003", 150000, "ahmad@ewallet", "123");
        
        // Kasus 3: Pembayaran E-Wallet - Validasi Berhasil
        Pembayaran ewSukses = new EWallet("TBK-004", 20000, "syarif@ewallet", "123456");
        
        // DEMO 1: Proses Pembayaran
        System.out.println("--- Proses Pembayaran ---");
        System.out.println("CASH (Status): " + cashOK.prosesPembayaran());
        System.out.println("E-Wallet (Gagal Status): " + ewGagal.prosesPembayaran());
        System.out.println("E-Wallet (Sukses Status): " + ewSukses.prosesPembayaran());
        System.out.println("-------------------------");
        
        // DEMO 2: Dynamic Binding melalui Interface (Receiptable)
        System.out.println("--- Cetak Struk ---");
        
        // Casting diperlukan karena cashOK dan ewSukses bertipe Pembayaran
        // dan kita ingin memanggil method dari interface Receiptable
        System.out.println(((Receiptable) cashOK).cetakStruk());
        System.out.println(((Receiptable) ewSukses).cetakStruk());
        System.out.println(((Receiptable) ewGagal).cetakStruk());

        // DEMO 3: Menggunakan Validatable (khusus EWallet)
        if (ewSukses instanceof Validatable) {
            Validatable v = (Validatable) ewSukses;
            System.out.println("\nValidasi OTP E-Wallet Sukses: " + v.validasi());
        }

        CreditBy.print("240202828", "akhmad akbar syarifudin");
    }
}
```
)
---

## Hasil Eksekusi
( Screenshot 2025-11-27 225004 )
---

## Analisis
(
- Jelaskan bagaimana kode berjalan. Kode menggunakan Pembayaran sebagai dasar polimorfik. Saat prosesPembayaran() dipanggil, Java menjalankan implementasi spesifik dari subclass (misalnya, Cash mengecek kecukupan uang, EWallet mengecek validasi() OTP). Pemanggilan cetakStruk() menunjukkan Dynamic Binding melalui Interface Receiptable, di mana method yang dieksekusi dipilih berdasarkan tipe objek nyata (runtime).

- Apa perbedaan pendekatan minggu ini dibanding minggu sebelumnya. Perbedaannya adalah fokus pada Kontrak Desain. Minggu ini, kami memisahkan logic inti (state dan method umum) ke dalam Abstract Class, sementara kemampuan tambahan (seperti Validatable atau Receiptable) didefinisikan secara terpisah menggunakan Interface. Ini memungkinkan Multiple Inheritance of Behavior yang tidak mungkin dilakukan dengan class tunggal.

- Kendala yang dihadapi dan cara mengatasinya. Kendala buatan yang dihadapi adalah compile error karena lupa mengimplementasikan semua method abstrak di class konkret. Ini diatasi dengan memastikan class konkret (Cash dan EWallet) menyediakan badan method untuk semua method yang diwarisi dari Pembayaran dan yang dikontrak oleh interface. 
)
---

## Kesimpulan
(Praktikum ini menegaskan bahwa Abstraksi efektif memisahkan tugas Abstract Class (Pembayaran) untuk shared state dan logic umum, dan Interface (Validatable, Receiptable) untuk mendefinisikan kemampuan. Pendekatan ini berhasil mendemonstrasikan Multiple Inheritance via Interface, membuat desain sistem lebih modular dan fleksibel.)

---

## Quiz
(
1. Jelaskan perbedaan konsep dan penggunaan abstract class dan interface. Jawaban: Abstract Class digunakan untuk basis class yang memiliki state dan perilaku dasar serupa ("adalah sejenis dari"). Sebaliknya, Interface hanya berisi kontrak method tanpa state dan digunakan untuk mendefinisikan kemampuan yang harus dimiliki class lintas hierarki ("memiliki kemampuan").

2. Mengapa multiple inheritance lebih aman dilakukan dengan interface pada Java? Jawaban: Multiple inheritance dengan interface lebih aman karena interface hanya menyediakan kontrak method dan tidak mewariskan state atau implementasi method konkrit. Hal ini mencegah terjadinya Diamond Problem—konflik state atau implementasi dari dua class induk berbeda—yang merupakan masalah utama pada pewarisan class ganda.

3. Pada contoh Agri-POS, bagian mana yang paling tepat menjadi abstract class dan mana yang menjadi interface? Jelaskan alasannya. Jawaban: Abstract Class yang paling tepat adalah Pembayaran, karena semua metode pembayaran memiliki shared state (invoiceNo, total) dan logic umum (totalBayar()). Interface yang paling tepat adalah Validatable dan Receiptable, karena keduanya adalah kemampuan tambahan yang tidak wajib dimiliki semua subkelas (misalnya, Validatable hanya diperlukan oleh EWallet).)
