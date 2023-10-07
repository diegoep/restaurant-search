package com.diegoep.restaurantsearch.util;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class CsvFileReader {

    private final ResourceLoader resourceLoader;

    public List<String[]> readCsvFile(String fileName) {
        List<String[]> values = new ArrayList<>();
        try(BufferedReader br = new BufferedReader(new InputStreamReader(resourceLoader.getResource("classpath:input/" + fileName).getInputStream()))) {
            String line;
            br.readLine().split(","); // skip first line [name, rating, distance, price, cuisine_id]
            while ((line = br.readLine()) != null) {
                values.add(line.split(","));
            }
        } catch (IOException e) {
            log.error("Error loading CSV file: " + e.getMessage());
        }
        return values;
    }

}
