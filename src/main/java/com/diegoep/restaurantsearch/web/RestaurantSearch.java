package com.diegoep.restaurantsearch.web;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

public record RestaurantSearch(
        String name,
        @Min(1) @Max(5) Integer rating,
        @Min(0) Long distance,
        String cuisine,
        @Min(0) Float price) { }

