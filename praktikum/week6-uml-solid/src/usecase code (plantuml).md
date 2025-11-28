@startuml
left to right direction
skinparam packageStyle rectangle

actor Kasir
actor Admin as "Admin / Manajer"

rectangle Sistem_taniku {
  usecase UC_Login as "Login"
  usecase UC_Laporan as "Lihat Laporan"
  usecase UC_Kelola as "Kelola Produk (gudang)"
  
  usecase UC_Checkout as "Proses Checkout"
  usecase UC_Cash as "Bayar Tunai"
  usecase UC_EWallet as "Bayar E-Wallet"
  usecase UC_Struk as "Cetak Struk"
}

' ---------------------------------
' ASOSIASI AKTOR
' ---------------------------------

Kasir --> UC_Checkout
Kasir --> UC_Struk
Kasir --> UC_Login
Admin --> UC_Login
Admin --> UC_Laporan
Admin --> UC_Kelola

' ---------------------------------
' HUBUNGAN USE CASE
' ---------------------------------

' Proses Checkout MENGGUNAKAN metode pembayaran (Include)
UC_Checkout <. UC_Cash : <<include>>
UC_Checkout <. UC_EWallet : <<include>>

' Cetak Struk membutuhkan Checkout  terlebih dahuli
UC_Struk    <. UC_Checkout : <<include>>

@enduml