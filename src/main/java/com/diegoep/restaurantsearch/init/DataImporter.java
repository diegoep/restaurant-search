package com.diegoep.restaurantsearch.init;

import com.diegoep.restaurantsearch.domain.Cuisine;
import com.diegoep.restaurantsearch.domain.Restaurant;
import com.diegoep.restaurantsearch.repository.CuisineRegistry;
import com.diegoep.restaurantsearch.repository.RestaurantRegistry;
import com.diegoep.restaurantsearch.searchindex.RestaurantIndex;
import com.diegoep.restaurantsearch.util.CsvFileReader;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
@RequiredArgsConstructor
public class DataImporter {

    private final RestaurantRegistry restaurantRegistry;
    private final CuisineRegistry cousineRegistry;

    private final RestaurantIndex restaurantIndex;
    private final CsvFileReader csvFileReader;

    @PostConstruct
    public void importData() {
        log.info("Importing data...");
        importRestaurants();
        importCuisines();
        log.info("Data imported");
    }

    private void importRestaurants() {
        List<String[]> lines = csvFileReader.readCsvFile("restaurants.csv");
        for (String[] values : lines) {
            String name = values[0];
            Integer rating = Integer.parseInt(values[1]);
            Long distance = Long.parseLong(values[2]);
            Float price = Float.parseFloat(values[3]);
            Integer cousineId = Integer.parseInt(values[4]);
            Restaurant restaurant = new Restaurant(name, rating, distance, price, cousineId);
            restaurantRegistry.addRestaurant(restaurant);
        }
        restaurantIndex.populateRestaurantIndexes(restaurantRegistry.findAllRestaurants());
    }

    private void importCuisines() {
        List<String[]> lines = csvFileReader.readCsvFile("cuisines.csv");
        for (String[] values : lines) {
            Integer id = Integer.parseInt(values[0]);
            String name = values[1];
            Cuisine cuisine = new Cuisine(id, name);
            cousineRegistry.addCuisine(cuisine);
        }
        restaurantIndex.populateCousineIndexes(cousineRegistry.findAllCuisines());
    }

}
