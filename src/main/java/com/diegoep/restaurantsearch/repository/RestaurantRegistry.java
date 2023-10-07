package com.diegoep.restaurantsearch.repository;

import com.diegoep.restaurantsearch.domain.Restaurant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.TreeMap;

@Component
@Slf4j
public class RestaurantRegistry {

    private final TreeMap<String, Restaurant> restaurants = new TreeMap<>();

    public void addRestaurant(Restaurant restaurant) {
        log.debug("Adding restaurant to registry: " + restaurant.getName());
        restaurants.put(restaurant.getName(), restaurant);
    }

    public void removeRestaurant(String name) {
        restaurants.remove(name);
    }

    public List<Restaurant> findAllRestaurants() {
        return List.copyOf(restaurants.values());
    }

    public Restaurant findRestaurantByName(String name) {
        return restaurants.get(name);
    }

}
