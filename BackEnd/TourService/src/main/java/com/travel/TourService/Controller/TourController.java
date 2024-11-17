package com.travel.TourService.Controller;

import com.travel.TourService.Entity.Tour;
import com.travel.TourService.Service.TourService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tour")
public class TourController {

    private final static Logger logger = LoggerFactory.getLogger(TourController.class);

    private TourService tourService;

    public TourController(TourService tourService) {
        this.tourService = tourService;
    }


    @PostMapping("/addTour")
    public ResponseEntity<String> addNewTour(@Valid @RequestBody Tour tour){
        Tour createdTour = tourService.addNewTour(tour);
        if(createdTour!=null){
            return new ResponseEntity<>("Tour created successfully", HttpStatus.CREATED);
        }
        return new ResponseEntity<>("Tour creation failed", HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/allTours")
    public List<Tour> getAllTours(){
        logger.info("All Tours!!");
        return tourService.getAllTours();
    }

    @GetMapping("/search")
    public Tour filterTourByLocation(@RequestParam String fromLocation, @RequestParam String toLocation){
        return tourService.filterTourByLocation(fromLocation, toLocation);
    }

}