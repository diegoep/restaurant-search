package com.diegoep.restaurantsearch.service;

import com.diegoep.restaurantsearch.domain.Restaurant;
import com.diegoep.restaurantsearch.web.RestaurantSearch;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


@SpringBootTest
public class RestaurantSearchServiceIntegrationTest {

    @Autowired
    private RestaurantSearchService restaurantSearchService;

    @Test
    public void testSearchByName() {
        RestaurantSearch criteria = new RestaurantSearch(
                "place",
                null,
                null,
                null,
                null
        );

        List<Restaurant> result = restaurantSearchService.searchRestaurants(criteria);

        assertEquals(3, result.size());
        assertEquals("Place Palace", result.get(0).getName());
        assertEquals("Place Chow", result.get(1).getName());
        assertEquals("Place Bar", result.get(2).getName());

    }

    @Test
    public void testSearchByRating() {
        RestaurantSearch criteria = new RestaurantSearch(
                null,
                4,
                null,
                null,
                null
        );

        List<Restaurant> result = restaurantSearchService.searchRestaurants(criteria);

        assertEquals(5, result.size());
        assertEquals("Deliciousgenix", result.get(0).getName());
        assertTrue( result.get(0).getRating() >= 4);
        assertEquals("Deliciouszilla", result.get(1).getName());
        assertTrue( result.get(1).getRating() >= 4);
        assertEquals("Fodder Table", result.get(2).getName());
        assertTrue( result.get(2).getRating() >= 4);
        assertEquals("Grove Table", result.get(3).getName());
        assertTrue( result.get(3).getRating() >= 4);
        assertTrue("Traditional Chow".equals(result.get(4).getName()) || "Bang Delicious".equals(result.get(4).getName())); // random order
        assertTrue( result.get(4).getRating() >= 4);

    }

    @Test
    public void testSearchByDistance() {
        RestaurantSearch criteria = new RestaurantSearch(
                null,
                null,
                3L,
                null,
                null
        );

        List<Restaurant> result = restaurantSearchService.searchRestaurants(criteria);

        assertEquals(5, result.size());
        assertEquals("Deliciousgenix", result.get(0).getName());
        assertTrue( result.get(0).getDistance() <= 3);
        assertEquals("Deliciouszilla", result.get(1).getName());
        assertTrue( result.get(1).getDistance() <= 4);
        assertEquals("Fodder Table", result.get(2).getName());
        assertTrue( result.get(2).getDistance() <= 4);
        assertEquals("Dished Grill", result.get(3).getName());
        assertTrue( result.get(3).getDistance() <= 4);
        assertEquals("Sizzle Yummy", result.get(4).getName());
        assertTrue( result.get(4).getDistance() <= 4);

    }

    @Test
    public void testSearchByCuisine() {
        RestaurantSearch criteria = new RestaurantSearch(
                null,
                null,
                null,
                "it", // Italian (id: 4)
                null
        );

        List<Restaurant> result = restaurantSearchService.searchRestaurants(criteria);

        assertEquals(5, result.size());
        assertEquals("Deliciouszoid", result.get(0).getName());
        assertTrue(result.get(0).getCuisineId() == 4);
        assertEquals("Tableque", result.get(1).getName());
        assertTrue(result.get(1).getCuisineId() == 4);
        assertEquals("Barscape", result.get(2).getName());
        assertTrue(result.get(2).getCuisineId() == 4);
        assertEquals("Palacearo", result.get(3).getName());
        assertTrue(result.get(3).getCuisineId() == 4);
        assertEquals("Fine Delicious", result.get(4).getName());
        assertTrue(result.get(4).getCuisineId() == 4);

    }

    @Test
    public void testSearchByPrice() {
        RestaurantSearch criteria = new RestaurantSearch(
                null,
                null,
                null,
                null,
                50.0f
        );

        List<Restaurant> result = restaurantSearchService.searchRestaurants(criteria);

        assertEquals(5, result.size());
        assertEquals("Deliciousgenix", result.get(0).getName());
        assertTrue(result.get(0).getPrice() <= 20.0f);
        assertEquals("Deliciouszilla", result.get(1).getName());
        assertTrue(result.get(1).getPrice() <= 20.0f);
        assertEquals("Fodder Table", result.get(2).getName());
        assertTrue(result.get(2).getPrice() <= 20.0f);
        assertEquals("Dished Grill", result.get(3).getName());
        assertTrue(result.get(3).getPrice() <= 20.0f);
        assertEquals("Sizzle Yummy", result.get(4).getName());
        assertTrue(result.get(4).getPrice() <= 20.0f);

    }

    @Test
    public void testCriteriaCombined() {
        RestaurantSearch criteria = new RestaurantSearch(
                "a",
                3,
                7L,
                "indo", // Indonesian (id: 17)
                60.0f
        );

        List<Restaurant> result = restaurantSearchService.searchRestaurants(criteria);

        System.out.println(result);

        assertEquals(2, result.size());
        assertEquals("Accent Yummy", result.get(0).getName());
        assertTrue(result.get(0).getRating() >= 3);
        assertTrue(result.get(0).getPrice() <= 60.0f);
        assertTrue(result.get(0).getCuisineId() == 17);
        assertTrue(result.get(0).getDistance() <= 7);
        assertEquals("Ambrosial Yummy", result.get(1).getName());
        assertTrue(result.get(1).getRating() >= 3);
        assertTrue(result.get(1).getPrice() <= 60.0f);
        assertTrue(result.get(1).getCuisineId() == 17);
        assertTrue(result.get(1).getDistance() <= 7);

    }

    @Test
    public void testNoResult() {
        RestaurantSearch criteria = new RestaurantSearch(
                "noresult",
                5,
                1L,
                "chi",
                10.0f
        );

        List<Restaurant> result = restaurantSearchService.searchRestaurants(criteria);

        assertEquals(0, result.size());

    }

}
