package tut_1;

public interface Queue<E> {
    void enqueue(E element);

    E dequeue();

    E front();

    int size();

    boolean isEmpty();
}
