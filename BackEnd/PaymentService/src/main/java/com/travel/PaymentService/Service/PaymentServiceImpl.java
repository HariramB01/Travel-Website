package com.travel.PaymentService.Service;

import com.travel.PaymentService.Entity.PaymentType;
import com.travel.PaymentService.Exception.NoSuchPaymentFound;
import com.travel.PaymentService.Request.PaymentRequest;
import com.travel.PaymentService.Service.Factory.PaymentProcessor;
import com.travel.PaymentService.Service.Factory.PaymentProcessorFactory;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl implements PaymentService {

    private final PaymentProcessorFactory paymentProcessorFactory;

    public PaymentServiceImpl(PaymentProcessorFactory paymentProcessorFactory) {
        this.paymentProcessorFactory = paymentProcessorFactory;
    }

    public String processPayment(PaymentRequest paymentRequest) throws NoSuchPaymentFound {
        PaymentProcessor paymentProcessor = paymentProcessorFactory.getPaymentProcessor(paymentRequest.getPaymentType());
        if (paymentProcessor == null) {
            throw new IllegalArgumentException("Invalid payment type: " + paymentRequest.getPaymentType());
        }
        return paymentProcessor.processPayment(paymentRequest.getBookingId(), paymentRequest.getUserId(), paymentRequest.getTotalAmount());
    }
}
