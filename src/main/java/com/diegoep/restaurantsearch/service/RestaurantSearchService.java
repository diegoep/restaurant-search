package com.diegoep.restaurantsearch.service;

import com.diegoep.restaurantsearch.searchindex.RestaurantIndex;
import com.diegoep.restaurantsearch.domain.Cuisine;
import com.diegoep.restaurantsearch.domain.Restaurant;
import com.diegoep.restaurantsearch.web.RestaurantSearch;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class RestaurantSearchService {

    private final RestaurantIndex restaurantIndex;

    public List<Restaurant> searchRestaurants(RestaurantSearch criteria) {

        List<Stream<Restaurant>> searchStreams = new ArrayList<>();

        if (criteria.name() != null) {
            searchStreams.add(restaurantIndex.getRestaurantNameIndex().search(criteria.name()).stream());
        }

        if (criteria.rating() != null) {
            searchStreams.add(restaurantIndex.getRatingIndex().getItemsGreaterOrEqualsThan(criteria.rating(), 5).stream());
        }

        if (criteria.distance() != null) {
            searchStreams.add(restaurantIndex.getDistanceIndex().getItemsLessOrEqualsThan(criteria.distance()).stream());
        }

        if (criteria.price() != null) {
            searchStreams.add(restaurantIndex.getPriceIndex().getItemsLessOrEqualsThan(criteria.price()).stream());
        }

        if (criteria.cuisine() != null) {
            Set<Cuisine> cuisineSearchResult = restaurantIndex.getCuisineNameIndex().search(criteria.cuisine());

            searchStreams.add(cuisineSearchResult.stream().
                    flatMap(cuisine -> restaurantIndex.getRestaurantByCuisineIndex().getItem(cuisine.getId()).stream()));
        }

        List<Restaurant> result = searchStreams.parallelStream(). // run parallel for better performance
        reduce((stream1, stream2) ->
            stream1.filter(stream2.collect(HashSet::new, Set::add, Set::addAll)::contains))
            .orElse(Stream.empty())
            .collect(Collectors.toList());

        sortResults(result);

        if (result.size() > 5) {
            result = result.stream().limit(5).collect(Collectors.toList());
        }

        return result;

    }

    private void sortResults(List<Restaurant> restaurants) {
        Comparator<Restaurant> byDistance = Comparator.comparing(Restaurant::getDistance);
        Comparator<Restaurant> byRating = Comparator.comparing(Restaurant::getRating).reversed();
        Comparator<Restaurant> byPrice = Comparator.comparing(Restaurant::getPrice);
        Comparator<Restaurant> randomComparator = (t1, t2) -> new Random().nextInt(3) - 1;  // Random value of -1, 0, or 1

        Comparator<Restaurant> sortCriteria = byDistance
                .thenComparing(byRating)
                .thenComparing(byPrice)
                .thenComparing(randomComparator);

        restaurants.sort(sortCriteria);
    }

}

