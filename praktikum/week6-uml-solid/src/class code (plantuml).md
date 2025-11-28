@startuml
title UML Class Diagram: Sistem Pembayaran taniku

hide circle

' ---------------------------------
' INTERFACE (Kontrak Kemampuan)
' ---------------------------------
interface Receiptable {
    + cetakStruk() : String
}

interface Validatable {
    + validasi() : boolean
}

' ---------------------------------
' ABSTRACT CLASS (Fondasi Pembayaran)
' ---------------------------------
abstract class Pembayaran {
    # invoiceNo : String
    # total : double
    --
    + Pembayaran(invoiceNo, total)
    + totalBayar() : double
    --
    {abstract} + biaya() : double
    {abstract} + prosesPembayaran() : boolean
}

' ---------------------------------
' CONCRETE CLASSES (Implementasi)
' ---------------------------------
class Cash {
    - tunai : double
    --
    + Cash(invoiceNo, total, tunai)
    + biaya() : double  // (0.0)
    + prosesPembayaran() : boolean // Cek Cukup?
    + cetakStruk() : String
}

class EWallet {
    - akun : String
    - otp : String
    --
    + EWallet(invoiceNo, total, akun, otp)
    + biaya() : double  // (1.5%)
    + prosesPembayaran() : boolean // Cek Saldo/Validasi
    + validasi() : boolean
    + cetakStruk() : String
}

' ---------------------------------
' RELATIONSHIPS
' ---------------------------------

' Pewarisan (Generalisasi)
Pembayaran <|-- Cash
Pembayaran <|-- EWallet

' Implementasi Interface (Realisasi)
Receiptable <|.. Cash
Receiptable <|.. EWallet

Validatable <|.. EWallet

@enduml