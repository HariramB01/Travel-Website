package com.travel.PaymentService.Service.Factory;

public interface PaymentProcessor {
    String processPayment(long bookingId, long userId, long totalAmount);
}
