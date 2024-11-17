package com.travel.PaymentService.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long paymentId;
    @Column(nullable = false)
    @NotNull(message = "bookingId is mandatory")
    private long bookingId;
    @Column(nullable = false)
    @NotNull(message = "userId is mandatory")
    private long userId;
    @Column(nullable = false)
    @NotNull(message = "totalAmount is mandatory")
    private long totalAmount;
    private PAYMENT_STATUS paymentStatus;
    private LocalDateTime paymentCreatedAt;


}
