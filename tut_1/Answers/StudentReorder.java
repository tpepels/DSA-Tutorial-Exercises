package tut_1.Answers;

public class StudentReorder {

    public static class LinkedList<E> {
        // Node and LinkedList implementation remains the same
        private static class Node<E> {
            E data;
            Node<E> next;

            Node(E data) {
                this.data = data;
                this.next = null;
            }
        }

        private Node<E> head;

        public LinkedList() {
            head = null;
        }

        @Override
        public void add(E data) {
            if (head == null) {
                head = new Node<>(data);
            } else {
                Node<E> current = head;
                while (current.next != null) {
                    current = current.next;
                }
                current.next = new Node<>(data);
            }
        }

        @Override
        public void remove(E data) {
            if (head == null) {
                return; // List is empty
            }
            if (head.data.equals(data)) {
                head = head.next; // Remove head
                return;
            }
            Node<E> current = head;
            Node<E> previous = null;
            while (current != null && !current.data.equals(data)) {
                previous = current;
                current = current.next;
            }
            if (current != null) {
                previous.next = current.next; // Remove the node
            }
        }

        @Override
        public boolean find(E data) {
            Node<E> current = head;
            while (current != null) {
                if (current.data.equals(data)) {
                    return true;
                }
                current = current.next;
            }
            return false;
        }

        @Override
        public void printList() {
            Node<E> current = head;
            while (current != null) {
                System.out.print(current.data + " ");
                current = current.next;
            }
            System.out.println();
        }

        @Override
        public int size() {
            int count = 0;
            Node<E> current = head;
            while (current != null) {
                count++;
                current = current.next;
            }
            return count;
        }

        // Method to reorder students
        public void reorder_students() {
            if (head == null || head.next == null) {
                return; // No need to reorder if list is empty or has only one element
            }

            // Find the middle of the list
            Node<E> slow = head;
            Node<E> fast = head;
            Node<E> previous = null;
            while (fast != null && fast.next != null) {
                previous = slow;
                slow = slow.next;
                fast = fast.next.next;
            }

            // Reverse the second half of the list
            Node<E> current = slow;
            Node<E> prev = null;
            Node<E> next;
            while (current != null) {
                next = current.next;
                current.next = prev;
                prev = current;
                current = next;
            }

            // Connect the two halves
            previous.next = prev;
        }
    }

    public static void main(String[] args) {
        // Test the reorder_students method
        LinkedList<String> studentList = new LinkedList<>();
        studentList.add("Student1");
        studentList.add("Student2");
        studentList.add("Student3");
        studentList.add("Student4");
        studentList.add("Student5");
        studentList.add("Student6");

        System.out.println("Original Order:");
        studentList.printList();

        studentList.reorder_students(); // Call the method from within the LinkedList class

        System.out.println("Reordered List:");
        studentList.printList();
    }
}
