package com.travel.PaymentService.Service.Factory;

import com.travel.PaymentService.Entity.PaymentType;
import com.travel.PaymentService.Exception.NoSuchPaymentFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class PaymentProcessorFactory {

    public PaymentProcessor getPaymentProcessor(String paymentType) throws NoSuchPaymentFound {
        if(paymentType.equalsIgnoreCase(String.valueOf(PaymentType.CREDIT_CARD))){
            return new CreditCardPaymentProcessor();
        }
        else if(paymentType.equalsIgnoreCase(String.valueOf(PaymentType.DEBIT_CARD))){
            return new DebitCardPaymentProcessor();
        }
        else if(paymentType.equalsIgnoreCase(String.valueOf(PaymentType.PAYPAL))){
            return new PayPalPaymentProcessor();
        }
        else if(paymentType.equalsIgnoreCase(String.valueOf(PaymentType.UPI))){
            return new UpiPaymentProcessor();
        }
        else{
            throw new NoSuchPaymentFound("Invalid payment type: " + paymentType);
        }
    }
}
