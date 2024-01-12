package tut_1;

// An example of a typical ADT (interface) for a list
public interface List<E> {
    void add(E data);

    void remove(E data);

    boolean find(E data);

    void printList();

    int size();
}
