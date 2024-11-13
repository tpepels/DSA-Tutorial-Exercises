import java.util.LinkedList;
import java.util.ListIterator;

public class TreasureInventoryManager {
    public static void main(String[] args) {
        // Example input LinkedLists
        LinkedList<Integer> LinkedListA = new LinkedList<>();
        LinkedListA.add(4);
        LinkedListA.add(5);
        LinkedListA.add(4);
        LinkedListA.add(7);

        LinkedList<Integer> LinkedListB = new LinkedList<>();
        LinkedListB.add(7);
        LinkedListB.add(8);
        LinkedListB.add(4);

        // Create a sorted list of unique items
        LinkedList<Integer> sortedUniqueList = new LinkedList<>();

        // Process LinkedListA
        for (int value : LinkedListA) {
            insertIntoSortedList(sortedUniqueList, value);
        }

        // Process LinkedListB
        for (int value : LinkedListB) {
            insertIntoSortedList(sortedUniqueList, value);
        }

        // Convert the sorted unique list into an array
        int[] sortedArray = new int[sortedUniqueList.size()];
        int index = 0;
        for (int value : sortedUniqueList) {
            sortedArray[index++] = value;
        }

        // Output the sorted array
        System.out.print("Sorted Array: [");
        for (int i = 0; i < sortedArray.length; i++) {
            System.out.print(sortedArray[i]);
            if (i < sortedArray.length - 1) {
                System.out.print(", ");
            }
        }
        System.out.println("]");
    }

    // Method to insert a value into the sorted list without duplicates
    public static void insertIntoSortedList(LinkedList<Integer> list, int value) {
        ListIterator<Integer> iterator = list.listIterator();
        while (iterator.hasNext()) {
            int currentValue = iterator.next();
            if (currentValue == value) {
                // Value already exists, do not insert
                return;
            } else if (currentValue > value) {
                // Insert value before currentValue
                iterator.previous();
                iterator.add(value);
                return;
            }
        }
        // If we reach here, value is greater than all elements, insert at end
        list.add(value);
    }
}
