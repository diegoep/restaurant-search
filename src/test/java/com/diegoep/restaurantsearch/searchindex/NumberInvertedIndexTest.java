package com.diegoep.restaurantsearch.searchindex;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class NumberInvertedIndexTest {

    private NumberInvertedIndex<Integer, String> invertedIndex;

    @BeforeEach
    void setUp() {
        invertedIndex = new NumberInvertedIndex<>();
    }

    @Test
    void testAddData() {
        // Arrange
        Integer number = 5;
        String data = "exampleData";

        // Act
        invertedIndex.addData(number, data);

        // Assert
        Set<String> invertedIndexMap = invertedIndex.getItem(5);
        assertNotNull(invertedIndexMap);
        assertTrue(invertedIndexMap.contains(data));
    }

    @Test
    void testGetItemsLessOrEqualsThan() {
        // Arrange
        Integer max = 10;
        String data1 = "data1";
        String data2 = "data2";
        String data3 = "data3";
        String data4 = "data4";

        invertedIndex.addData(5, data1);
        invertedIndex.addData(7, data2);
        invertedIndex.addData(10, data3);
        invertedIndex.addData(11, data4);

        // Act
        Set<String> result = invertedIndex.getItemsLessOrEqualsThan(max);

        // Assert
        assertEquals(3, result.size());
        assertTrue(result.contains(data1));
        assertTrue(result.contains(data2));
        assertTrue(result.contains(data3));
    }

    @Test
    void testGetItemsGreaterOrEqualsThan() {
        // Arrange
        Integer min = 2;
        Integer max = 7;
        String data1 = "data1";
        String data2 = "data2";
        String data3 = "data3";
        String data4 = "data4";

        invertedIndex.addData(2, data1);
        invertedIndex.addData(5, data2);
        invertedIndex.addData(7, data3);
        invertedIndex.addData(8 , data4);

        // Act
        Set<String> result = invertedIndex.getItemsGreaterOrEqualsThan(min, max);

        // Assert
        assertEquals(3, result.size());
        assertTrue(result.contains(data1));
        assertTrue(result.contains(data2));
        assertTrue(result.contains(data3));
    }

    @Test
    void testGetItem() {
        // Arrange
        Integer number = 5;
        String data1 = "data1";
        String data2 = "data2";

        invertedIndex.addData(number, data1);
        invertedIndex.addData(number, data2);

        // Act
        Set<String> result = invertedIndex.getItem(number);

        // Assert
        assertEquals(2, result.size());
        assertTrue(result.contains(data1));
        assertTrue(result.contains(data2));
    }

}
