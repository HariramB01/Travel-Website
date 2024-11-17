package com.travel.TourService.Repository;

import com.travel.TourService.Entity.Tour;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TourRepository extends JpaRepository<Tour, Long> {
    Tour findByFromLocationAndToLocation(String fromLocation, String toLocation);
}