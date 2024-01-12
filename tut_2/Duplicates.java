package tut_2;

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
}
