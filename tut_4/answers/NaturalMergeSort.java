public class NaturalMergeSort {
    public static void naturalMergeSort(int[] array) {
        if (array.length <= 1) {
            return; // The array is already sorted
        }

        int[] auxiliary = new int[array.length];
        while (true) {
            // Step 1: Identify runs and merge pairs of runs
            int runCount = 0;
            int i = 0;

            while (i < array.length) {
                // Start of a new run
                int start = i;
                // Identify the run in ascending order
                while (i + 1 < array.length && array[i] <= array[i + 1]) {
                    i++;
                }

                // For descending runs (optional)
                // while (i + 1 < array.length && array[i] >= array[i + 1]) {
                //     i++;
                // }

                // reverse(array, start, i);

                i++; // Move to the next element after the run
                runCount++;
                // If there's another run, merge them
                if (i < array.length) {
                    int start2 = i;
                    // Identify the next run
                    while (i + 1 < array.length && array[i] <= array[i + 1]) {
                        i++;
                    }
                    i++; // Move to the next element after the second run

                    runCount++;
                    // Merge the two runs
                    merge(array, auxiliary, start, start2 - 1, i - 1);
                }
            }
            if (runCount <= 1) {
                // The array is fully sorted
                break;
            }}}

    // Merge two consecutive runs
    private static void merge(int[] array, int[] auxiliary, int leftStart, int rightStart, int rightEnd) {
        int leftEnd = rightStart - 1;
        int auxIndex = leftStart;
        int start = leftStart;
        int left = leftStart;
        int right = rightStart;
        while (left <= leftEnd && right <= rightEnd) {
            if (array[left] <= array[right]) {
                auxiliary[auxIndex++] = array[left++];
            } else {
                auxiliary[auxIndex++] = array[right++];
            }
        }
        while (left <= leftEnd) {
            auxiliary[auxIndex++] = array[left++];
        }
        while (right <= rightEnd) {
            auxiliary[auxIndex++] = array[right++];
        }
        // Copy the merged run back into the original array
        for (int i = start; i <= rightEnd; i++) {
            array[i] = auxiliary[i];
        }
    }

    // (Optional) Reverse a run if descending order is considered
    private static void reverse(int[] array, int start, int end) {
        while (start < end) {
            int temp = array[start];
            array[start++] = array[end];
            array[end--] = temp;
        }
    }

    // Helper method to print the array

    private static void printArray(int[] array) {
        System.out.print("Sorted Array: [");
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i]);
            if (i < array.length - 1)
                System.out.print(", ");
        }
        System.out.println("]");
    }

    // Example usage
    public static void main(String[] args) {
        int[] array = {
            1, 2, 5, 4, 3, 7, 8, 10, 9, 6, 12, 11, 15, 14, 13 };

        System.out.print("Original Array: [");
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i]);
            if (i < array.length - 1)
                System.out.print(", ");
        }
        System.out.println("]");
        naturalMergeSort(array);
        printArray(array);
    }}
