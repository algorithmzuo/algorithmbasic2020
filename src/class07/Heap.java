package class07;

import java.util.List;

public interface Heap<T> {
    boolean isEmpty();

    int size();

    boolean contains(T obj);

    T peek();

    void push(T obj);

    T pop();

    void remove(T obj);

    void resign(T obj);

    List<T> getAllElements();

    void heapInsert(int index);

    void heapify(int index);

}
