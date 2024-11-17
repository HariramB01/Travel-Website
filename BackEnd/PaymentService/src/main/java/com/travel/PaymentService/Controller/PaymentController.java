package com.travel.PaymentService.Controller;

import com.travel.PaymentService.Entity.PaymentType;
import com.travel.PaymentService.Exception.NoSuchPaymentFound;
import com.travel.PaymentService.Request.PaymentRequest;
import com.travel.PaymentService.Service.PaymentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payment")
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping("/process")
    public ResponseEntity<String> processPayment(@RequestBody PaymentRequest paymentRequest) throws NoSuchPaymentFound {
        String message = paymentService.processPayment(paymentRequest);
        return ResponseEntity.ok(message);
    }
}
