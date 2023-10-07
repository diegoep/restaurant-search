package com.diegoep.restaurantsearch.init;

import com.diegoep.restaurantsearch.domain.Cuisine;
import com.diegoep.restaurantsearch.domain.Restaurant;
import com.diegoep.restaurantsearch.repository.CuisineRegistry;
import com.diegoep.restaurantsearch.repository.RestaurantRegistry;
import com.diegoep.restaurantsearch.searchindex.RestaurantIndex;
import com.diegoep.restaurantsearch.util.CsvFileReader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class DataImporterTest {

    @Mock
    private RestaurantRegistry restaurantRegistry;

    @Mock
    private CuisineRegistry cuisineRegistry;

    @Mock
    private RestaurantIndex restaurantIndex;

    @Mock
    private CsvFileReader csvFileReader;

    private DataImporter dataImporter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        dataImporter = new DataImporter(restaurantRegistry, cuisineRegistry, restaurantIndex, csvFileReader);
    }

    @Test
    void testImportData() {
        // Mock CSV file data
        List<String[]> restaurantData = new ArrayList<>();
        restaurantData.add(new String[] { "Restaurant1", "4", "100", "2.5", "1" });
        restaurantData.add(new String[] { "Restaurant2", "3", "200", "3.0", "2" });

        List<String[]> cuisineData = new ArrayList<>();
        cuisineData.add(new String[] { "1", "Italian" });
        cuisineData.add(new String[] { "2", "Japanese" });

        // Mock CSV file reading behavior
        when(csvFileReader.readCsvFile("restaurants.csv")).thenReturn(restaurantData);
        when(csvFileReader.readCsvFile("cuisines.csv")).thenReturn(cuisineData);

        // Call the importData method
        dataImporter.importData();

        // Verify that methods were called with the expected data
        verify(restaurantRegistry, times(2)).addRestaurant(any(Restaurant.class));
        verify(restaurantIndex).populateRestaurantIndexes(anyList());
        verify(cuisineRegistry, times(2)).addCuisine(any(Cuisine.class));
        verify(restaurantIndex).populateCousineIndexes(anyList());
    }

}
