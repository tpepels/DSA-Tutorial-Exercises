package tut_4.answers;

import java.util.Arrays;

public class PancakeSorter {

    public static void main(String[] args) {
        int[] pancakes = { 1, 7, 4, 6, 2, 8, 3, 5 };
        sortPancakes(pancakes);
        System.out.println(Arrays.toString(pancakes)); // Expected sorted output
    }

    public static int[] sortPancakes(int[] pancakes) {
        int n = pancakes.length;

        for (int size = n; size > 1; size--) {
            int maxIndex = findMaxIndex(pancakes, size);

            if (maxIndex != size - 1) {
                if (maxIndex != 0) {
                    pancakes = spatulaFlip(maxIndex, pancakes);
                }
                pancakes = spatulaFlip(size - 1, pancakes);
            }
        }
        return pancakes;
    }

    private static int findMaxIndex(int[] pancakes, int size) {
        int maxIndex = 0;
        for (int i = 1; i < size; i++) {
            if (pancakes[i] > pancakes[maxIndex]) {
                maxIndex = i;
            }
        }
        return maxIndex;
    }

    private static int[] spatulaFlip(int pos, int[] pancakes) {
        int start = 0;
        while (start < pos) {
            int temp = pancakes[start];
            pancakes[start] = pancakes[pos];
            pancakes[pos] = temp;
            start++;
            pos--;
        }
        return pancakes;
    }
}
