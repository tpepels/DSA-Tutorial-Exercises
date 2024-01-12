package tut_2.Answers;

import tut_2.Dictionary;

public class ChainingHashMap implements Dictionary {
    private static class Entry {
        String key;
        int value;
        Entry next;

        Entry(String key, int value) {
            this.key = key;
            this.value = value;
            this.next = null;
        }
    }

    private final Entry[] table;
    private final int capacity;

    public ChainingHashMap(int capacity) {
        this.capacity = capacity;
        this.table = new Entry[capacity];
    }

    // Polynomial rolling hash function for strings
    private int hash(String key) {
        int hashValue = 0;
        int prime = 31;
        for (int i = 0; i < key.length(); i++) {
            hashValue = (prime * hashValue + key.charAt(i)) % capacity;
        }
        return hashValue;
    }

    // Implement the put method using chaining for collision resolution
    @Override
    public void put(String key, int value) {
        int index = hash(key);
        Entry newEntry = new Entry(key, value);
        if (table[index] == null) {
            table[index] = newEntry;
        } else {
            Entry current = table[index];
            while (current.next != null) {
                if (current.key.equals(key)) {
                    current.value = value; // Update existing key
                    return;
                }
                current = current.next;
            }
            if (current.key.equals(key)) {
                current.value = value; // Update existing key
            } else {
                current.next = newEntry; // Add new entry
            }
        }
    }

    // Implement the get method
    @Override
    public int get(String key) {
        int index = hash(key);
        Entry current = table[index];
        while (current != null) {
            if (current.key.equals(key)) {
                return current.value;
            }
            current = current.next;
        }
        return -1; // Key not found
    }

    public static void main(String[] args) {
        Dictionary map = new ChainingHashMap(10);

        // Test putting key-value pairs
        map.put("key1", 10);
        map.put("key2", 20);
        map.put("key3", 30);
        map.put("key4", 40);

        // Test getting values
        System.out.println("key1: " + map.get("key1")); // Expected: 10
        System.out.println("key2: " + map.get("key2")); // Expected: 20
        System.out.println("key3: " + map.get("key3")); // Expected: 30
        System.out.println("key4: " + map.get("key4")); // Expected: 40

        // Test updating an existing key
        map.put("key1", 100);
        System.out.println("key1 updated: " + map.get("key1")); // Expected: 100

        // Test getting a value for a non-existing key
        System.out.println("key5: " + map.get("key5")); // Expected: 0 or null, depending on implementation

        // Test collision handling
        // Depending on the hash function, these keys might collide.
        // TODO Make sure to test a case where chaining is needed by finding a collision
        map.put("a", 1);
        map.put("b", 2);
        System.out.println("a: " + map.get("a")); // Expected: 1
        System.out.println("b: " + map.get("b")); // Expected: 2

        // Additional tests can be added here
    }
}
