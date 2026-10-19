package com.kampusfood;

import com.kampusfood.checkout.CheckoutFacade;
import com.kampusfood.menu.BundlePackage;
import com.kampusfood.menu.MenuCategory;
import com.kampusfood.menu.MenuItem;
import com.kampusfood.model.CheckoutResult;
import com.kampusfood.model.Order;
import com.kampusfood.model.OrderLine;
import com.kampusfood.notification.channel.EmailChannel;
import com.kampusfood.notification.channel.NotificationChannel;
import com.kampusfood.notification.channel.WhatsAppChannel;
import com.kampusfood.payment.PaymentMethod;
import com.kampusfood.payment.adapter.NewPayServiceAdapter;
import com.kampusfood.payment.adapter.OldPayGatewayAdapter;
import com.kampusfood.payment.external.NewPayService;
import com.kampusfood.payment.legacy.OldPayGateway;
import com.kampusfood.service.DeliveryService;
import com.kampusfood.service.InventoryService;
import com.kampusfood.service.NotificationService;
import com.kampusfood.service.PaymentService;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("==================================================");
        System.out.println("         DEMO APLIKASI KAMPUSFOOD                 ");
        System.out.println("==================================================");

        // 1. Bangun Menu (Composite Pattern)
        System.out.println("\n[Skenario 1] Membangun Pohon Menu:");
        MenuCategory rootMenu = new MenuCategory("Menu KampusFood");

        MenuCategory makananCategory = new MenuCategory("Makanan");
        MenuCategory nasiSubCategory = new MenuCategory("Nasi");
        nasiSubCategory.add(new MenuItem("Nasi Goreng", 15000.0));
        nasiSubCategory.add(new MenuItem("Ayam Geprek", 18000.0));
        makananCategory.add(nasiSubCategory);

        MenuCategory minumanCategory = new MenuCategory("Minuman");
        minuminCategoryItemSetup(minumanCategory); // helper method or direct add
        
        // Bundle Package: Paket Hemat (Ayam Geprek + Es Teh, diskon 20%)
        BundlePackage paketHemat = new BundlePackage("Paket Hemat", 0.20);
        paketHemat.add(new MenuItem("Ayam Geprek", 18000.0));
        paketHemat.add(new MenuItem("Es Teh", 5000.0));

        rootMenu.add(makananCategory);
        rootMenu.add(minumanCategory);
        rootMenu.add(paketHemat);

        rootMenu.display(0);

        // 2. Setup Services & Facade
        InventoryService inventoryService = new InventoryService();
        PaymentService paymentService = new PaymentService();
        DeliveryService deliveryService = new DeliveryService();
        NotificationService notificationService = new NotificationService();

        CheckoutFacade checkoutFacade = new CheckoutFacade(
                inventoryService, paymentService, deliveryService, notificationService
        );

        // Daftarkan Payment Methods (Adapter Pattern)
        paymentService.register("OLD", new OldPayGatewayAdapter(new OldPayGateway()));
        paymentService.register("NEW", new NewPayServiceAdapter(new NewPayService()));

        // Buat Order untuk Skenario A & B & C & Ekstensi
        MenuItem nasiGoreng = new MenuItem("Nasi Goreng", 15000.0);
        MenuItem esTeh = new MenuItem("Es Teh", 5000.0);

        Order orderA = new Order("ORD-001", "user-mhs-1", List.of(
                new OrderLine(nasiGoreng, 2),
                new OrderLine(esTeh, 2)
        ));

        Order orderB = new Order("ORD-002", "user-mhs-2", List.of(
                new OrderLine(paketHemat, 1)
        ));

        Order orderC = new Order("ORD-003", "user-mhs-3", List.of(
                new OrderLine(nasiGoreng, 1)
        ));

        // 3. Skenario A: checkout dengan "OLD" + EmailChannel (sukses)
        System.out.println("\n--------------------------------------------------");
        System.out.println("Skenario A: Checkout dengan Pembayaran 'OLD' dan EmailChannel");
        System.out.println("--------------------------------------------------");
        CheckoutResult resA = checkoutFacade.checkout(orderA, "OLD", new EmailChannel());
        System.out.println("Hasil A: " + resA.getMessage() + " (Sukses: " + resA.isSuccess() + ")");

        // 4. Skenario B: checkout dengan "NEW" + WhatsAppChannel (sukses)
        System.out.println("\n--------------------------------------------------");
        System.out.println("Skenario B: Checkout dengan Pembayaran 'NEW' dan WhatsAppChannel");
        System.out.println("--------------------------------------------------");
        CheckoutResult resB = checkoutFacade.checkout(orderB, "NEW", new WhatsAppChannel());
        System.out.println("Hasil B: " + resB.getMessage() + " (Sukses: " + resB.isSuccess() + ")");

        // 5. Skenario C: checkout dengan tipe tidak dikenal "XYZ" -> gagal, stok di-release
        System.out.println("\n--------------------------------------------------");
        System.out.println("Skenario C: Checkout dengan Tipe Pembayaran Tidak Dikenal 'XYZ'");
        System.out.println("--------------------------------------------------");
        CheckoutResult resC = checkoutFacade.checkout(orderC, "XYZ", new EmailChannel());
        System.out.println("Hasil C: " + resC.getMessage() + " (Sukses: " + resC.isSuccess() + ")");

        // 6. Skalabilitas: Tambah QrisAdapter (Anonymous/Inline) & TelegramChannel (tanpa mengubah kelas lama)
        System.out.println("\n--------------------------------------------------");
        System.out.println("Skenario D: Skalabilitas (QrisAdapter & TelegramChannel)");
        System.out.println("--------------------------------------------------");
        
        // Inline / Anonymous PaymentMethod implementation as QrisAdapter
        PaymentMethod qrisAdapter = new PaymentMethod() {
            @Override
            public boolean pay(String userId, double amount, String currency) {
                System.out.println("[QRIS Adapter] Memproses QRIS instan untuk " + userId + " sebesar Rp " + amount);
                return true;
            }
        };

        // Inline / Anonymous NotificationChannel implementation as TelegramChannel
        NotificationChannel telegramChannel = new NotificationChannel() {
            @Override
            public void send(String recipient, String subject, String body) {
                System.out.printf("[Telegram -> %s] 🤖 [%s] %s%n", recipient, subject, body);
            }
        };

        paymentService.register("QRIS", qrisAdapter);

        Order orderExt = new Order("ORD-004", "user-mhs-4", List.of(
                new OrderLine(paketHemat, 2)
        ));

        CheckoutResult resExt = checkoutFacade.checkout(orderExt, "QRIS", telegramChannel);
        System.out.println("Hasil Ekstensi: " + resExt.getMessage() + " (Sukses: " + resExt.isSuccess() + ")");

        System.out.println("\n==================================================");
        System.out.println("         DEMO KAMPUSFOOD SELESAI                  ");
        System.out.println("==================================================");
    }

    private static void minuminCategoryItemSetup(MenuCategory minumanCategory) {
        minumanCategory.add(new MenuItem("Es Teh", 5000.0));
        minumanCategory.add(new MenuItem("Kopi Kampus", 10000.0));
    }
}
