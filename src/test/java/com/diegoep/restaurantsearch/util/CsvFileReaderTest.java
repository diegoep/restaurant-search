package com.diegoep.restaurantsearch.util;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import java.io.*;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

public class CsvFileReaderTest {

    @InjectMocks
    private CsvFileReader csvFileReader;

    @Mock
    private ResourceLoader resourceLoader;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testReadCsvFile() throws IOException {
        // Arrange
        String testFileName = "test.csv";
        String testCsvData = "name,rating,distance,price,cuisine_id\n" +
                "Restaurant1,4.5,2.5,$$,1\n" +
                "Restaurant2,4.0,3.0,$$$,2\n";

        // Create a mock InputStream for the resourceLoader
        Resource resource = Mockito.mock(Resource.class);
        InputStream mockInputStream = new ByteArrayInputStream(testCsvData.getBytes());
        when(resource.getInputStream()).thenReturn(mockInputStream);
        when(resourceLoader.getResource("classpath:input/" + testFileName)).thenReturn(resource);

        // Act
        List<String[]> values = csvFileReader.readCsvFile(testFileName);

        // Assert
        assertNotNull(values);
        assertEquals(2, values.size());
        assertEquals("Restaurant1", values.get(0)[0]);
        assertEquals("4.5", values.get(0)[1]);
        assertEquals("2.5", values.get(0)[2]);
        assertEquals("$$", values.get(0)[3]);
        assertEquals("1", values.get(0)[4]);
        assertEquals("Restaurant2", values.get(1)[0]);
        assertEquals("4.0", values.get(1)[1]);
        assertEquals("3.0", values.get(1)[2]);
        assertEquals("$$$", values.get(1)[3]);
        assertEquals("2", values.get(1)[4]);

    }
}

