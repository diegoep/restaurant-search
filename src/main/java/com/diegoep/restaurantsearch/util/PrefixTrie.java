package com.diegoep.restaurantsearch.util;

import com.diegoep.restaurantsearch.domain.Restaurant;

import java.util.HashSet;
import java.util.Set;

class TrieNode<T> {
    TrieNode[] children;

    Set<T> data = new HashSet<>();

    TrieNode() {
        children = new TrieNode[26]; // Assuming lowercase English alphabet
    }

}

public class PrefixTrie<T> {
    private TrieNode root;

    public PrefixTrie() {
        root = new TrieNode();
    }

    public void insert(String word, T data) {
        TrieNode<T> node = root;
        for (char c : word.toCharArray()) {
            int index = c - 'a';
            if (node.children[index] == null) {
                node.children[index] = new TrieNode();
            }
            node = node.children[index];
            node.data.add(data);
        }
    }

    public Set<T> search(String word) {
        TrieNode node = root;
        for (char c : word.toCharArray()) {
            int index = c - 'a';
            if (node.children[index] == null) {
                return node.data;
            }
            node = node.children[index];
        }
        return node.data;
    }

    public static void main(String[] args) {
        PrefixTrie<Restaurant> trie = new PrefixTrie();

        trie.insert("mc", new Restaurant("mc donalds", 1, 1L, 1.0f, 1));
        trie.insert("mcdon",  new Restaurant("mc donalds", 1, 1L, 1.0f, 1));
        trie.insert("kf",  new Restaurant("KFC", 1, 1L, 1.0f, 1));
        trie.insert("kf",  new Restaurant("KFA", 1, 1L, 1.0f, 1));
        trie.insert("piz", new Restaurant("Pizza Hut", 1, 1L, 1.0f, 1));

        System.out.println(trie.search("mc"));   // mc donalds
        System.out.println(trie.search("mc don"));   // mc donalds
        System.out.println(trie.search("KF"));  // KFC, KFA
        System.out.println(trie.search("Pizza Hut"));  // Pizza Hut
        System.out.println(trie.search("piz"));     // Pizza Hut

    }
}

