package com.diegoep.restaurantsearch.web;

import com.diegoep.restaurantsearch.domain.Restaurant;
import com.diegoep.restaurantsearch.service.RestaurantSearchService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Tag(name = "Restaurant Search API", description = "Restaurant Search API")
public class RestaurantSearchController {

    private final RestaurantSearchService restaurantSearchService;

    @GetMapping("/search")
    @Operation(summary = "Search for restaurants")
    public List<Restaurant> search(@Validated RestaurantSearch restaurantSearch) {
        return restaurantSearchService.searchRestaurants(restaurantSearch);
    }

}
