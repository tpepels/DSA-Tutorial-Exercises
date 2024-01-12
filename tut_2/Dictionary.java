package tut_2;

public interface Dictionary {
    /**
     * Inserts or updates a key-value pair in the dictionary.
     * If the key already exists, its associated value is updated.
     * 
     * @param key   The key to be inserted or updated.
     * @param value The value to be associated with the key.
     */
    void put(String key, int value);

    /**
     * Retrieves the value associated with a given key.
     * 
     * @param key The key whose value is to be retrieved.
     * @return The value associated with the key, or -1 if the key is not found.
     */
    int get(String key);
}
