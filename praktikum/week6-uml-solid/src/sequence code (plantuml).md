@startuml
title Interaksi Pembayaran Checkout taniku

actor Kasir
participant CheckoutService
participant PaymentFactory
participant CashPayment
participant EWalletPayment
participant PaymentGateway

-> CheckoutService : processCheckout(items, methodType, amountReceived)

activate CheckoutService
    CheckoutService -> PaymentFactory : createPayment(methodType)
    activate PaymentFactory
        alt methodType == "TUNAI"
            PaymentFactory -> CashPayment : <<create>>
            activate CashPayment
            PaymentFactory <-- CashPayment : CashPayment Instance
            deactivate CashPayment
        else methodType == "EWALLET"
            PaymentFactory -> EWalletPayment : <<create>>
            activate EWalletPayment
            PaymentFactory <-- EWalletPayment : EWalletPayment Instance
            deactivate EWalletPayment
        end
    CheckoutService <-- PaymentFactory : paymentObject
    deactivate PaymentFactory

    CheckoutService -> PMethod : prosesPembayaran(amountReceived)
    activate PMethod
        alt paymentObject is CashPayment (Tunai)
            PMethod -> CashPayment : prosesPembayaran(amountReceived)
            activate CashPayment
                note right: Validasi Uang Fisik / Cek Kecukupan Uang
                CashPayment -> CashPayment : uangCukup = (amountReceived >= totalBayar())
                alt uangCukup == true
                    CashPayment --> CheckoutService : status = BERHASIL
                else uangCukup == false
                    CashPayment --> CheckoutService : status = GAGAL (Uang Kurang)
                end
            deactivate CashPayment
        else paymentObject is EWalletPayment
            PMethod -> EWalletPayment : prosesPembayaran()
            activate EWalletPayment
                note right: Validasi OTP / Cek Saldo Gateway
                EWalletPayment -> PaymentGateway : authorize(totalBayar())
                activate PaymentGateway
                    PaymentGateway --> EWalletPayment : status_gateway
                deactivate PaymentGateway

                alt status_gateway == BERHASIL
                    EWalletPayment --> CheckoutService : status = BERHASIL
                else status_gateway == GAGAL
                    EWalletPayment --> CheckoutService : status = GAGAL (Saldo Kurang)
                end
            deactivate EWalletPayment
        end

    deactivate PMethod

    alt status == BERHASIL
        CheckoutService -> CheckoutService : updateStokDB()
        CheckoutService -> CashPayment : cetakStruk()
        CheckoutService -> EWalletPayment : cetakStruk()
        Kasir <- CheckoutService : Struk Tercetak & Kembalian
    else status == GAGAL
        Kasir <- CheckoutService : Pesan Gagal Pembayaran
    end

deactivate CheckoutService
@enduml