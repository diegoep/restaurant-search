package com.diegoep.restaurantsearch.searchindex;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class PartialStringInvertedIndexTest {

    private PartialStringInvertedIndex<String> partialStringInvertedIndex;

    @BeforeEach
    void setUp() {
        partialStringInvertedIndex = new PartialStringInvertedIndex<>();
    }

    @Test
    void testAddData() {
        // Arrange
        String word = "restaurant";
        String data = "exampleData";

        // Act
        partialStringInvertedIndex.addData(word, data);

        // Assert
        Set<String> result = partialStringInvertedIndex.search(word);
        assertTrue(result.contains(data));
    }

    @Test
    void testSearch() {
        // Arrange
        String word = "food";

        partialStringInvertedIndex.addData(word, "data1");
        partialStringInvertedIndex.addData(word, "data2");

        // Act
        Set<String> result = partialStringInvertedIndex.search(word);

        // Assert
        assertTrue(result.containsAll(List.of("data1", "data2")));
    }


}
