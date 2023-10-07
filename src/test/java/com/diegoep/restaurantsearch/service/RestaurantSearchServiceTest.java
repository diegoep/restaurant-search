package com.diegoep.restaurantsearch.service;


import com.diegoep.restaurantsearch.domain.Restaurant;
import com.diegoep.restaurantsearch.searchindex.RestaurantIndex;
import com.diegoep.restaurantsearch.web.RestaurantSearch;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InOrder;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.inOrder;

public class RestaurantSearchServiceTest {

    @Spy
    private RestaurantIndex restaurantIndex;

    private RestaurantSearchService restaurantSearchService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        restaurantSearchService = new RestaurantSearchService(restaurantIndex);
    }

    @Test
    void testSearchRestaurants() {
        // Create some sample data
        RestaurantSearch criteria = new RestaurantSearch(
                "RestaurantName",
                4,
                10L,
                "CuisineName",
                20.00f
        );

        // Call the method under test
        List<Restaurant> result = restaurantSearchService.searchRestaurants(criteria);

        InOrder inOrder = inOrder(restaurantIndex);
        inOrder.verify(restaurantIndex).getRestaurantNameIndex();
        inOrder.verify(restaurantIndex).getRatingIndex();
        inOrder.verify(restaurantIndex).getDistanceIndex();
        inOrder.verify(restaurantIndex).getPriceIndex();
        inOrder.verify(restaurantIndex).getCuisineNameIndex();

        // Assertions
        assertEquals(result.size(), 0);
    }
}
