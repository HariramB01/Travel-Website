package com.travel.PaymentService.Service.Factory;

import org.springframework.stereotype.Component;

@Component
public class DebitCardPaymentProcessor implements PaymentProcessor {
    @Override
    public String processPayment(long bookingId, long userId, long totalAmount) {
        System.out.println("BookingId: "+bookingId+" UserId: "+userId+" Total Amount: "+totalAmount);
        return "Processing debit card payment";
    }
}