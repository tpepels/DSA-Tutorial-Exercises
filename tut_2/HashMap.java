package tut_2;

public class HashMap implements Dictionary {
    private static class Entry {
        String key;
        int value;

        Entry(String key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    private final int capacity;
    private final Entry[] table;

    public HashMap(int capacity) {
        this.capacity = capacity;
        this.table = new Entry[capacity];
    }

    // Hash function for strings
    private int hash(String key) {
        int hashValue = 0;
        int prime = 31;
        for (int i = 0; i < key.length(); i++) {
            hashValue = (prime * hashValue + key.charAt(i)) % capacity;
        }
        return hashValue;
    }

    // a/b Implement the put method using chaining for collision resolution
    @Override
    public void put(String key, int value) {
        // Your implementation here
    }

    // a/b Implement the get method
    @Override
    public int get(String key) {
        // Your implementation here
    }

    // b. Implement a secondary hash function for double hashing
    private int secondaryHash(String key) {
        // Your implementation here
    }

    // Main method for testing your CustomHashMap
    public static void main(String[] args) {
        Dictionary map = new HashMap(10);

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
        map.put("a", 1);
        map.put("b", 2);
        System.out.println("a: " + map.get("a")); // Expected: 1
        System.out.println("b: " + map.get("b")); // Expected: 2

        // Additional tests can be added here
    }
}
