package com.travel.PaymentService.Service.Factory;

import org.springframework.stereotype.Component;

@Component
public class CreditCardPaymentProcessor implements PaymentProcessor {
    @Override
    public String processPayment(long bookingId, long userId, long totalAmount) {
        System.out.println("BookingId: "+bookingId+" UserId: "+userId+" Total Amount: "+totalAmount);
        return "Processing credit card payment";
    }
}