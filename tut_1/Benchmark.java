package tut_1;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class Benchmark {

    public static void main(String[] args) {
        int arraySize = 5000;
        int n_arrays = 1000;
        // In case the algorithm is too fast, we can increase the number of arrays or
        // the size of the arrays. Try different inputs to see how they affect the
        // results.
        List<int[]> randomArrays = generateRandomArrays(arraySize, n_arrays); // Generate random arrays
        System.out.println("Checking if results match...");
        benchmark(randomArrays, 3); // Version 3 checks the output of both methods to see if they match

        // Benchmark naive approach
        long naiveTime = benchmark(randomArrays, 1);
        System.out.println("Naive approach time: " + naiveTime + " milliseconds for " + n_arrays + " arrays of size "
                + arraySize + " each");

        // Benchmark optimized approach
        long optimizedTime = benchmark(randomArrays, 2);
        System.out.println("Optimized approach time: " + optimizedTime + " milliseconds  for " + n_arrays
                + " arrays of size " + arraySize + " each");
    }

    private static long benchmark(List<int[]> randomArrays, int version) {
        long startTime = System.currentTimeMillis();

        for (int[] randomArray : randomArrays) {
            if (version == 1) {
                sumOfUniqueElements(randomArray.clone());
            } else if (version == 2) {
                sumOfUniqueElementsOptimized(randomArray.clone());
            } else if (version == 3) {
                // Compare results
                int naiveResult = sumOfUniqueElements(randomArray.clone());
                int optimizedResult = sumOfUniqueElementsOptimized(randomArray.clone());
                if (naiveResult != optimizedResult) {
                    System.out.println("Results do not match!");
                    System.exit(1);
                }
            }
        }

        long endTime = System.currentTimeMillis();
        // Calculate the average execution time
        return endTime - startTime;
    }

    private static List<int[]> generateRandomArrays(int size, int count) {
        List<int[]> arrays = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < count; i++) {
            int[] array = new int[size];
            for (int j = 0; j < size; j++) {
                array[j] = random.nextInt(10000); // Change the upper bound as needed
            }
            arrays.add(array);
        }
        return arrays;
    }

    // Finds the sum of all unique elements in an array
    public static int sumOfUniqueElements(int[] array) {
        int sum = 0;
        for (int i = 0; i < array.length; i++) {
            boolean isUnique = true;
            for (int j = 0; j < array.length; j++) {
                if (i != j && array[i] == array[j]) {
                    isUnique = false;
                    break;
                }
            }
            if (isUnique) {
                sum += array[i];
            }
        }
        return sum;
    }

    // Optimized sumOfUniqueElements method
    public static int sumOfUniqueElementsOptimized(int[] array) {
        Set<Integer> uniqueElements = new HashSet<>();
        Set<Integer> duplicates = new HashSet<>();

        for (int value : array) {
            if (!uniqueElements.add(value)) {
                duplicates.add(value);
            }
        }

        int sum = 0;
        for (int value : uniqueElements) {
            if (!duplicates.contains(value)) {
                sum += value;
            }
        }

        return sum;
    }
}
