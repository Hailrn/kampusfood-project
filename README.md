# KampusFood

KampusFood adalah aplikasi pesan-antar makanan berbasis konsol (console app) untuk lingkungan kampus yang dibangun menggunakan **Java 17 murni** tanpa framework atau dependensi eksternal. Proyek ini difokuskan untuk mendemonstrasikan penerapan **Structural Design Patterns** secara bersih dan sesuai prinsip *Object-Oriented Programming* (OOP) serta *SOLID Principles* (khususnya *Open-Closed Principle*).

---

## Penerapan Design Patterns

1. **Adapter Pattern (`payment`)**
   - Mengintegrasikan berbagai sistem pembayaran eksternal dan legacy (`OldPayGateway`, `NewPayService`) ke dalam interface terpadu `PaymentMethod`.
   - Konversi mata uang/satuan dari `double amount` ke `int cents` diisolasi sepenuhnya di dalam `OldPayGatewayAdapter` (`Math.round(amount * 100)`). Kelas legacy `OldPayGateway` tidak diubah sama sekali.

2. **Composite Pattern (`menu`)**
   - Menghubungkan item menu tunggal (`MenuItem`) dan kumpulan menu/kategori/bundel (`MenuCategory`, `BundlePackage`) melalui interface seragam `MenuComponent`.
   - Perhitungan harga dan tampilan rekursif dilakukan secara polimorfik tanpa menggunakan `instanceof`.
   - `BundlePackage` mendukung perhitungan diskon otomatis terhadap sub-komponennya.

3. **Bridge Pattern (`notification`)**
   - Memisahkan hierarki jenis notifikasi (`Notification` sebagai *Abstraction* dengan turunan seperti `OrderConfirmationNotification`, `PromoNotification`, `DeliveryUpdateNotification`) dari kanal pengiriman (`NotificationChannel` sebagai *Implementor* dengan turunan seperti `EmailChannel`, `SmsChannel`, `WhatsAppChannel`, `PushChannel`).
   - Mencegah ledakan kombinasi kelas (misalnya tidak perlu kelas `PromoEmail`).

4. **Facade Pattern (`checkout`)**
   - Menyediakan titik masuk terpadu `CheckoutFacade` bagi klien/Main untuk mengoordinasikan subsistem terkait (`InventoryService`, `PaymentService`, `DeliveryService`, `NotificationService`).
   - Alur transaksi (reservasi stok -> penagihan -> penjadwalan -> pengiriman & notifikasi, atau pelepasan stok jika gagal) dienkapsulasi di dalam Facade.

---

## Struktur Direktori

```
src/main/java/com/kampusfood/
├── Main.java
├── model/
│   ├── Order.java
│   ├── OrderLine.java
│   └── CheckoutResult.java
├── payment/
│   ├── PaymentMethod.java          // Target
│   ├── legacy/OldPayGateway.java   // Adaptee (Legacy)
│   ├── external/NewPayService.java // Adaptee
│   └── adapter/
│       ├── OldPayGatewayAdapter.java // Adapter
│       └── NewPayServiceAdapter.java // Adapter
├── menu/
│   ├── MenuComponent.java          // Component
│   ├── MenuItem.java               // Leaf
│   ├── MenuComposite.java          // Composite
│   ├── MenuCategory.java           // Composite
│   └── BundlePackage.java          // Composite
├── notification/
│   ├── Notification.java           // Abstraction
│   ├── OrderConfirmationNotification.java // RefinedAbstraction
│   ├── PromoNotification.java      // RefinedAbstraction
│   ├── DeliveryUpdateNotification.java // RefinedAbstraction
│   └── channel/
│       ├── NotificationChannel.java // Implementor
│       ├── EmailChannel.java       // ConcreteImplementor
│       ├── SmsChannel.java         // ConcreteImplementor
│       ├── WhatsAppChannel.java    // ConcreteImplementor
│       └── PushChannel.java        // ConcreteImplementor
├── service/
│   ├── InventoryService.java
│   ├── PaymentService.java
│   ├── DeliveryService.java
│   └── NotificationService.java
└── checkout/
    └── CheckoutFacade.java         // Facade
```

---

## Requirements

- **Java Development Kit (JDK) 17** atau versi di atasnya.
- Terminal / Command Line Interface (Bash, Zsh, Command Prompt, dll.).

---

## Cara Menjalankan Proyek

1. Buka terminal pada direktori root proyek (`kampusfood-project`).
2. Buat direktori output (`out`) dan kompilasi seluruh file Java:
   ```bash
   javac --release 17 -d out $(find src -name "*.java")
   ```
   *(Jika menggunakan Windows PowerShell/CMD, Anda dapat mendaftarkan path file secara manual atau menggunakan skrip kompiler).*

3. Jalankan aplikasi:
   ```bash
   java -cp out com.kampusfood.Main
   ```
