package com.kampusfood.checkout;

import com.kampusfood.model.CheckoutResult;
import com.kampusfood.model.Order;
import com.kampusfood.notification.channel.NotificationChannel;
import com.kampusfood.service.DeliveryService;
import com.kampusfood.service.InventoryService;
import com.kampusfood.service.NotificationService;
import com.kampusfood.service.PaymentService;

// Facade
public class CheckoutFacade {
    private final InventoryService inventoryService;
    private final PaymentService paymentService;
    private final DeliveryService deliveryService;
    private final NotificationService notificationService;

    public CheckoutFacade(InventoryService inventoryService,
                          PaymentService paymentService,
                          DeliveryService deliveryService,
                          NotificationService notificationService) {
        this.inventoryService = inventoryService;
        this.paymentService = paymentService;
        this.deliveryService = deliveryService;
        this.notificationService = notificationService;
    }

    public CheckoutResult checkout(Order order, String paymentType, NotificationChannel channel) {
        System.out.println("\n--- Memulai Proses Checkout untuk Order #" + order.getId() + " ---");
        
        // 1. Reserve stok
        boolean reserved = inventoryService.reserve(order);
        if (!reserved) {
            return new CheckoutResult(false, "Gagal memesan stok.");
        }

        // 2. Charge pembayaran
        boolean charged = paymentService.charge(order, paymentType);
        if (!charged) {
            inventoryService.release(order);
            return new CheckoutResult(false, "Pembayaran gagal. Stok dilepas kembali.");
        }

        // 3. Schedule pengiriman
        String trackingCode = deliveryService.schedule(order);

        // 4. Kirim notifikasi konfirmasi & update pengiriman
        notificationService.orderConfirmed(order, channel);
        notificationService.deliveryUpdate(order, channel);

        System.out.println("--- Checkout Selesai Berhasil (Tracking: " + trackingCode + ") ---\n");
        return new CheckoutResult(true, "Checkout berhasil dengan kode lacak: " + trackingCode);
    }
}
