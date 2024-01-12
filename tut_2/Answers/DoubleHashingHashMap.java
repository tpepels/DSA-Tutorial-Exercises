package tut_2.Answers;

import tut_2.Dictionary;

public class DoubleHashingHashMap implements Dictionary {
    private static class Entry {
        String key;
        int value;

        Entry(String key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    private Entry[] table;
    private int capacity;

    public DoubleHashingHashMap(int capacity) {
        this.capacity = capacity;
        this.table = new Entry[capacity];
    }

    // Primary hash function
    private int hash(String key) {
        int hashValue = key.hashCode() % capacity;
        return (hashValue < 0) ? hashValue + capacity : hashValue;
    }

    // Secondary hash function - computes a step size for probing on collision
    private int secondaryHash(String key) {
        int hashValue = 1 + (key.hashCode() % (capacity - 1));
        return (hashValue < 0) ? hashValue + capacity - 1 : hashValue;
    }

    // Method to put key-value pairs. Uses double hashing for collision resolution
    @Override
    public void put(String key, int value) {
        int hash = hash(key);
        int stepSize = secondaryHash(key);
        int initialHash = -1;

        // Loop until an empty slot is found or the entire table is traversed
        while (table[hash] != null && !table[hash].key.equals(key)) {
            if (initialHash == -1) {
                initialHash = hash; // Store the first hash value
            } else if (hash == initialHash) { // Detect cycle and exit if entire table traversed
                return;
            }

            // Apply double hashing: increment the hash by the step size and wrap around the
            // table
            hash = (hash + stepSize) % capacity;
        }

        // Place the new entry in the table
        table[hash] = new Entry(key, value);
    }

    // Method to get value by key. Uses double hashing for searching the value
    @Override
    public int get(String key) {
        int hash = hash(key);
        int stepSize = secondaryHash(key);
        int initialHash = -1;

        // Loop until the matching key is found or an empty slot is reached
        while (table[hash] != null) {
            if (table[hash].key.equals(key)) {
                return table[hash].value; // Return the found value
            }

            if (initialHash == -1) {
                initialHash = hash; // Store the first hash value
            } else if (hash == initialHash) { // Detect cycle and exit if entire table traversed
                return -1;
            }

            // Apply double hashing: increment the hash by the step size and wrap around the
            // table
            hash = (hash + stepSize) % capacity;
        }

        return -1; // Key not found
    }

    public static void main(String[] args) {
        DoubleHashingHashMap map = new DoubleHashingHashMap(10);

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
        // TODO Make sure to test a case where the secondary hash function is needed by
        // finding a collision
        map.put("ababa", 1);
        map.put("bababa", 2);
        System.out.println("a: " + map.get("a")); // Expected: 1
        System.out.println("b: " + map.get("b")); // Expected: 2

        // Additional tests can be added here
    }
}
