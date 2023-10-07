package com.diegoep.restaurantsearch.util;

import com.diegoep.restaurantsearch.domain.Restaurant;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PrefixTrieTest {
    @Test
    void testInsertAndSearch() {
        PrefixTrie<Restaurant> trie = new PrefixTrie<>();

        Restaurant mcdonalds = new Restaurant("McDonald's", 1, 1L, 1.0f, 1);
        Restaurant kfc = new Restaurant("KFC", 1, 1L, 1.0f, 1);
        Restaurant kfa = new Restaurant("KFA", 1, 1L, 1.0f, 1);
        Restaurant pizzaHut = new Restaurant("Pizza Hut", 1, 1L, 1.0f, 1);

        trie.insert("mc", mcdonalds);
        trie.insert("mcdon", mcdonalds);
        trie.insert("kf", kfc);
        trie.insert("kf", kfa);
        trie.insert("piz", pizzaHut);

        // Test search for existing prefixes
        assertEquals(Set.of(mcdonalds), trie.search("mc"));
        assertEquals(Set.of(mcdonalds), trie.search("mcdon"));
        assertEquals(Set.of(kfc, kfa), trie.search("kf"));
        assertEquals(Set.of(pizzaHut), trie.search("piz"));

        // Test search for non-existing prefixes
        assertEquals(Set.of(), trie.search("burger"));
        assertEquals(Set.of(), trie.search("biz"));

    }
}
