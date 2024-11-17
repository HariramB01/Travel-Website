package com.travel.PaymentService.Exception;

public class NoSuchPaymentFound extends Throwable {
    public NoSuchPaymentFound(String message) {
        super(message);
    }
}
