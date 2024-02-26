package tut_4.answers;

public class QuickSelect {

    public static int quickSelect(int[] A, int k) {
        return quickSelect(A, 0, A.length - 1, k - 1);
    }

    private static int quickSelect(int[] A, int low, int high, int k) {
        if (low == high)
            return A[low];

        int pivotIndex = partition(A, low, high);
        if (k == pivotIndex)
            return A[k];
        else if (k < pivotIndex)
            return quickSelect(A, low, pivotIndex - 1, k);
        else
            return quickSelect(A, pivotIndex + 1, high, k);
    }

    private static int partition(int[] A, int low, int high) {
        int pivot = A[high];
        int i = low;
        for (int j = low; j < high; j++) {
            if (A[j] <= pivot) {
                swap(A, i, j);
                i++;
            }
        }
        swap(A, i, high);
        return i;
    }

    private static void swap(int[] A, int i, int j) {
        int temp = A[i];
        A[i] = A[j];
        A[j] = temp;
    }

    public static void main(String[] args) {
        int[] A = { 29, 28, 35, 20, 9, 33, 8, 9, 11, 6, 21, 28, 18, 36, 1 };
        int k = 6;
        System.out.println("The " + k + "th smallest element is: " + quickSelect(A, k));
    }
}
