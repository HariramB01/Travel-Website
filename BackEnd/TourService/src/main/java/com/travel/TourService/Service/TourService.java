package com.travel.TourService.Service;

import com.travel.TourService.Entity.Tour;

import java.util.List;

public interface TourService {

     Tour addNewTour(Tour tour);

     List<Tour> getAllTours();

     Tour filterTourByLocation(String fromLocation, String toLocation);
}
