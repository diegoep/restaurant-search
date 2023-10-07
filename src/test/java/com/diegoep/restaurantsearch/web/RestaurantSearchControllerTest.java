package com.diegoep.restaurantsearch.web;

import com.diegoep.restaurantsearch.domain.Restaurant;
import com.diegoep.restaurantsearch.service.RestaurantSearchService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@WebMvcTest(RestaurantSearchController.class)
public class RestaurantSearchControllerTest {

    private MockMvc mockMvc;

    @MockBean
    private RestaurantSearchService restaurantSearchService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = standaloneSetup(new RestaurantSearchController(restaurantSearchService)).build();
    }

    @Test
    void testSearchRestaurants() throws Exception {
        // Arrange
        RestaurantSearch searchCriteria = new RestaurantSearch(null, 3, null, null, null);
        List<Restaurant> searchResults = Collections.singletonList(new Restaurant("Restaurant 1", 4, 2L, 25.0f, 1));

        when(restaurantSearchService.searchRestaurants(any((RestaurantSearch.class)))).thenReturn(searchResults);

        // Act & Assert
        mockMvc.perform(get("/api/search")
                        .param("rating", "3")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].name").value("Restaurant 1"))
                .andExpect(jsonPath("$[0].rating").value(4))
                .andExpect(jsonPath("$[0].distance").value(2))
                .andExpect(jsonPath("$[0].price").value(25.0))
                .andExpect(jsonPath("$[0].cuisineId").value(1));
    }

    @Test
    void testSearchRestaurantsWithInvalidValue() throws Exception {
        // Act & Assert
        mockMvc.perform(get("/api/search")
                        .param("rating", "6")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }
}
