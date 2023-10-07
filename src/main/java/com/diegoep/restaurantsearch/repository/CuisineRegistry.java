package com.diegoep.restaurantsearch.repository;

import com.diegoep.restaurantsearch.domain.Cuisine;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.TreeMap;

@Component
@Slf4j
public class CuisineRegistry {

    private final TreeMap<Integer, Cuisine> cuisines = new TreeMap<>();

    public void addCuisine(Cuisine cuisine) {
        log.debug("Adding cousine to registry: " + cuisine.getName());
        cuisines .put(cuisine.getId(), cuisine);
    }

    public void removeCuisine(String name) {
        cuisines.remove(name);
    }

    public List<Cuisine> findAllCuisines() {
        return List.copyOf(cuisines.values());
    }

    public Cuisine findCuisineById(Integer id) {
        return cuisines.get(id);
    }

}
