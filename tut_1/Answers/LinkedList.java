package tut_1.Answers;

import tut_1.List;

public class LinkedList<E> implements List<E> {

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

    // Return the data at a specific index (optional challenge)
    public E get(int index) {
        if (index < 0) {
            throw new IndexOutOfBoundsException("Index cannot be negative: " + index);
        }
        Node<E> current = head;
        for (int i = 0; current != null && i < index; i++) {
            current = current.next;
        }
        if (current == null) {
            throw new IndexOutOfBoundsException("Index out of bounds: " + index);
        }
        return current.data;
    }

    // Add a node at a specific index (optional challenge)
    public void add(int index, E data) {
        if (index == 0) {
            Node<E> newNode = new Node<>(data);
            newNode.next = head;
            head = newNode;
            return;
        }
        Node<E> current = head;
        for (int i = 0; current != null && i < index - 1; i++) {
            current = current.next;
        }
        if (current == null) {
            throw new IndexOutOfBoundsException("Index: " + index);
        }
        Node<E> newNode = new Node<>(data);
        newNode.next = current.next;
        current.next = newNode;
    }
}
