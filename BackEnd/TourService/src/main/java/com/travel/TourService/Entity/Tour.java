package com.travel.TourService.Entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Tour {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long tourId;

    @Column(nullable = false)
    @NotNull(message = "fromLocation is mandatory")
    private String fromLocation;

    @Column(nullable = false)
    @NotNull(message = "toLocation is mandatory")
    private String toLocation;

    @Column(nullable = false)
    private long totalKms;

    @Column(nullable = false)
    private long noOfAvailableSeats;

    @Column(nullable = false)
    @NotNull(message = "startDate is mandatory")
    private LocalDate startDate;

    @Column(nullable = false)
    @NotNull(message = "endDate is mandatory")
    private LocalDate endDate;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "tour_id")
    @NotNull(message = "foodMenu is mandatory")
    private List<Menu> foodMenu;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "tour_id")
    @NotNull(message = "places is mandatory")
    private List<VisitingPlace> places;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "tour_id")
    @NotNull(message = "activities is mandatory")
    private List<Activity> activities;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @NotNull(message = "vehicleType is mandatory")
    private VEHICLE_TYPE vehicleType;

    @Column(nullable = false)
    private long price;
}
