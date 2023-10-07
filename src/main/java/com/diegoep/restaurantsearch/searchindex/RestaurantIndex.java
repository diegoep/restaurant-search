package com.diegoep.restaurantsearch.searchindex;

import com.diegoep.restaurantsearch.domain.Cuisine;
import com.diegoep.restaurantsearch.domain.Restaurant;
import lombok.Getter;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Getter
public class RestaurantIndex {

    private final NumberInvertedIndex<Float, Restaurant> priceIndex = new NumberInvertedIndex<>();
    private final NumberInvertedIndex<Integer, Restaurant> ratingIndex = new NumberInvertedIndex<>();
    private final NumberInvertedIndex<Long, Restaurant> distanceIndex = new NumberInvertedIndex<>();
    private final NumberInvertedIndex<Integer, Restaurant> restaurantByCuisineIndex = new NumberInvertedIndex();
    private final PartialStringInvertedIndex<Restaurant> restaurantNameIndex = new PartialStringInvertedIndex();
    private final PartialStringInvertedIndex<Cuisine> cuisineNameIndex = new PartialStringInvertedIndex();

    public void populateRestaurantIndexes(List<Restaurant> restaurants) {
        restaurants.forEach(restaurant -> {
            priceIndex.addData(restaurant.getPrice(), restaurant);
            ratingIndex.addData(restaurant.getRating(), restaurant);
            distanceIndex.addData(restaurant.getDistance(), restaurant);
            restaurantNameIndex.addData(restaurant.getName(), restaurant);
            restaurantByCuisineIndex.addData(restaurant.getCuisineId(), restaurant);
        });
    }

    public void populateCousineIndexes(List<Cuisine> cuisines) {
        cuisines.forEach(cuisine -> {
            cuisineNameIndex.addData(cuisine.getName(), cuisine);
        });
    }

}
