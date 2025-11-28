@startuml
title Proses Checkout dan Pembayaran taniku

|Kasir (User)|
start
:Scan Produk terpilih ;
|Sistem|
:Hitung Total harga;

if (Pilih Media?) then (cesh)
  |Kasir (User)|
  :Input Uang yang Diterima;
  |Sistem|
  if (Uang sesuai?) then (cukup)
    :Transaksi dilakukan;
  else (kurang)
    :Tolak & menegur;
    stop
  endif
|Sistem|
elseif (E-Many)
  :Kirim Permintaan jalur Payment dengan apa ;
  if (Saldo Cukup?) then (Ya)
    :proses & Berhasil;
  else (Tidak)
    :Gagal & Kembali ke pilih media;
    stop
  endif
endif

|Sistem|
:Catat dan eksekusi Transaksi;
:Update Stok Database;
:Cetak Struk Transaksi;
stop
@enduml