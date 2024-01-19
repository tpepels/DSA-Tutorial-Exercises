package tut_2;

import java.util.Map;
import java.util.HashMap;

public class Duplicates {
    public static boolean containsKDuplicates(int[] array, int k) {
        for (int i = 0; i < array.length; i++) {
            int count = 0;
            for (int j = 0; j < array.length; j++) {
                if (array[i] == array[j]) {
                    count++;
                }
                if (count >= k) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean containsKDuplicatesOptimized(int[] array, int k) {
        if (array == null || array.length < k) {
            return false;
        }

        Map<Integer, Integer> countDict = new HashMap<>();

        for (int element : array) {
            int count = countDict.getOrDefault(element, 0);
            countDict.put(element, count + 1);

            if (countDict.get(element) >= k) {
                return true;
            }
        }

        return false;
    }

    public static void main(String[] args) {
        // Example usage
        int[] array = { 1, 2, 3, 4, 1, 2, 1 };
        int k = 3;

        boolean result = containsKDuplicatesOptimized(array, k);
        System.out.println("Contains at least " + k + " duplicates: " + result);
    }
}
