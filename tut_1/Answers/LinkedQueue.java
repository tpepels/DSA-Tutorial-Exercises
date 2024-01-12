package tut_1.Answers;

import java.util.NoSuchElementException;

import tut_1.Queue;

public class LinkedQueue<E> implements Queue<E> {
    private LinkedList<E> list;

    public LinkedQueue() {
        list = new LinkedList<>();
    }

    @Override
    public void enqueue(E element) {
        list.add(element);
    }

    @Override
    public E dequeue() {
        if (isEmpty()) {
            // Throwing the exception here is optional but recommended
            throw new NoSuchElementException("Queue is empty");
        }
        E frontElement = list.get(0);
        list.remove(frontElement);
        return frontElement;
    }

    @Override
    public E front() {
        if (isEmpty()) {
            // Throwing the exception here is optional but recommended
            throw new NoSuchElementException("Queue is empty");
        }
        return list.get(0);
    }

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public boolean isEmpty() {
        return list.size() == 0;
    }
}
