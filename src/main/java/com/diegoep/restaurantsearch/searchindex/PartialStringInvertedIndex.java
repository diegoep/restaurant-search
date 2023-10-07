package com.diegoep.restaurantsearch.searchindex;

import com.diegoep.restaurantsearch.util.PrefixTrie;

import java.util.Set;

public class PartialStringInvertedIndex<V> {

    private PrefixTrie<V> invertedIndex = new PrefixTrie<>();

    public void addData(String word, V data) {
        invertedIndex.insert(word.trim().toLowerCase().replaceAll("[\\s\\p{Punct}]", ""), data);
    }

    public Set<V> search(String word) {
        return invertedIndex.search(word.trim().toLowerCase().replaceAll("[\\s\\p{Punct}]", ""));
    }

}
