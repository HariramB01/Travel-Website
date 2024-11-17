package com.travel.PaymentService.Service;

import com.travel.PaymentService.Exception.NoSuchPaymentFound;
import com.travel.PaymentService.Request.PaymentRequest;

public interface PaymentService {
    String processPayment(PaymentRequest paymentRequest) throws NoSuchPaymentFound;
}
