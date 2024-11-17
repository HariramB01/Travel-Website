package com.travel.TourService.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.travel.TourService.Entity.Tour;
import com.travel.TourService.Repository.TourRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class TourServiceImpl implements TourService {

    private static final Logger logger = LoggerFactory.getLogger(TourServiceImpl.class);

    private final RedisTemplate<String, Object> redisTemplate;
    private final TourRepository tourRepository;

    private static final String CACHE_KEY_PREFIX = "tour:";
    private static final String CACHE_KEY_ALL = CACHE_KEY_PREFIX + "all";

    public TourServiceImpl(RedisTemplate<String, Object> redisTemplate, TourRepository tourRepository) {
        this.redisTemplate = redisTemplate;
        this.tourRepository = tourRepository;
    }

    @Override
    @CacheEvict(value = {"allTours", "filteredTours"}, allEntries = true)
    public Tour addNewTour(Tour tour) {
        logger.info("Adding new tour: {}", tour);
        return tourRepository.save(tour);
    }

    @Override
    public List<Tour> getAllTours() {
        logger.info("Fetching all tours");
        List<Tour> tours = (List<Tour>) redisTemplate.opsForValue().get(CACHE_KEY_ALL);

        if (tours == null) {
            logger.info("Cache miss. Fetching tours from the database...");
            tours = tourRepository.findAll();
            if (!tours.isEmpty()) {
                redisTemplate.opsForValue().setIfAbsent(CACHE_KEY_ALL, tours, 30, TimeUnit.SECONDS);
                logger.info("Tours cached successfully");
            }
        } else {
            logger.info("Cache hit. Returning tours from Redis...");
        }

        return tours;
    }

    @Override
    public Tour filterTourByLocation(String fromLocation, String toLocation) {
        String cacheKey = CACHE_KEY_PREFIX + fromLocation + "-" + toLocation;
        logger.info("Filtering tours from {} to {}", fromLocation, toLocation);

        // Check cache first
        Object cachedData = redisTemplate.opsForValue().get(cacheKey);
        if (cachedData != null) {
            logger.info("Cache hit for tours from {} to {}", fromLocation, toLocation);
            try {
                ObjectMapper objectMapper = new ObjectMapper();
                objectMapper.registerModule(new JavaTimeModule());
                return objectMapper.convertValue(cachedData, Tour.class);
            } catch (IllegalArgumentException e) {
                logger.error("Error converting cached data: {}", e.getMessage());
            }
        }

        // Fetch from database if not in cache
        Tour tourFromDB = tourRepository.findByFromLocationAndToLocation(fromLocation, toLocation);
        if (tourFromDB != null) {
            redisTemplate.opsForValue().setIfAbsent(cacheKey, tourFromDB);
            logger.info("Data fetched from DB and cached for tours from {} to {}", fromLocation, toLocation);
            return tourFromDB;
        }

        logger.warn("No tours found from {} to {}", fromLocation, toLocation);
        return null;
    }

//    @Override
//    public Tour updateTour(Long id, Tour tour) {
//        logger.info("Updating tour with ID: {}", id);
//
//        Tour existingTour = tourRepository.findById(id).orElseThrow(
//                () -> new IllegalArgumentException("Tour with ID: " + id + " not found")
//        );
//
//        existingTour.setFromLocation(tour.getFromLocation());
//        existingTour.setToLocation(tour.getToLocation());
//        existingTour.setDescription(tour.getDescription());
//        existingTour.setPrice(tour.getPrice());
//
//        tourRepository.save(existingTour);
//
//        // Update cache
//        String cacheKey = CACHE_KEY_PREFIX + existingTour.getFromLocation() + "-" + existingTour.getToLocation();
//        redisTemplate.opsForValue().set(cacheKey, existingTour);
//        logger.info("Tour updated and cache refreshed: {}", existingTour);
//
//        return existingTour;
//    }
}
