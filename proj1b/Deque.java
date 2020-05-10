public interface Deque<T> {

    void addFirst(T item);

    void addLast(T item);

    void printDeque();

    T removeFirst();

    T removeLast();

    T get(int index);

    int size();

    /** Return true if the deque is empty, false otherwise */
    default boolean isEmpty() {
        return size() == 0;
    }


    
}
