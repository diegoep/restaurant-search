package com.diegoep.restaurantsearch.searchindex;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class NumberInvertedIndex<T extends Number, V> {

    private TreeMap<T, Set<V>> invertedIndex = new TreeMap<>();

    public void addData(T number, V data) {
        invertedIndex.computeIfAbsent(number, k -> new HashSet<>()).add(data);
    }

    public Set<V> getItemsLessOrEqualsThan(T max) {
        return invertedIndex.headMap(max, true).values()
                .stream()
                .flatMap(Set::stream)
                .collect(Collectors.toSet());
    }

    public Set<V> getItemsGreaterOrEqualsThan(T min, T max) {
        return invertedIndex.subMap(min, true , max, true).values()
                .stream()
                .flatMap(Set::stream)
                .collect(Collectors.toSet());
    }

    public Set<V> getItem(T number) {
        return invertedIndex.get(number);
    }

}
