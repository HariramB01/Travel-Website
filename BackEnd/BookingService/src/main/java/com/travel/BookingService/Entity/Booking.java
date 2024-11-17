package com.travel.BookingService.Entity;

import jakarta.persistence.*;
import lombok.*;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long bookingId;

    @Column(nullable = false)
    private int numberOfPersons;

    @Column(nullable = false)
    private long tourId;

    @Column(nullable = false)
    private long userId;

    @Column(nullable = false)
    private long totalAmount;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @NotNull(message = "booking status is mandatory")
    private BOOKING_STATUS status;

    private LocalDateTime bookingCreatedAt;

}
