/** Array-base Deque, using a circle array represents the deque
 * Invariant:
 * when you add a element to the head of deque, you insert it at nextHead
 * when you add a element to the end of deque, you insert it at nextEnd
 * @param <T>
 */
public class ArrayDeque<T> {

    private T[] items;
    private int size;
    private int nextHead;
    private int nextEnd;
    private final double LOADFACTOR = 0.25;

    public ArrayDeque() {
        items = (T[]) new Object[8];
        size = 0;
        nextHead = 7;
        nextEnd = 0;
    }

    public ArrayDeque(ArrayDeque other) {
        items = (T[]) new Object[other.size];
        size = other.size;
        System.arraycopy(other.items, 0, items, 0, size);
        nextHead = other.nextHead;
        nextEnd = other.nextEnd;
    }

    /* Add the element to the head of deque, if the array is full resize it */
    public void addFirst(T item) {
        if (size >= items.length) {
            resize(2 * items.length);
        }
        items[nextHead] = item;
        nextHead = (nextHead - 1) % items.length;
        size = size + 1;
    }

    /* Add the element to the end of deque, it the array is full resize it */
    public void addLast(T item) {
        if (size >= items.length) {
            resize(2 * items.length);
        }
        items[nextEnd] = item;
        nextEnd = (nextEnd + 1) % items.length;
        size = size + 1;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    /* Resize the array with the capacity */
    private void resize(int capacity) {
        T[] newItems = (T[]) new Object[capacity];
        System.arraycopy(items, (nextHead-1)%items.length, newItems, 0, size);
        items = newItems;
    }

    public void printDeque() {

        int head = (nextHead - 1) % items.length;
        for (int i = 0; i < size; i++) {
            System.out.print(items[head]);
            head = (head + 1) % items.length;
        }
    }

    /* Remove the first element of deque, resize it if too sparse */
    public T removeFirst() {
        if ((size - 1)/items.length < LOADFACTOR) {
            resize(items.length / 2);
        }
        nextHead = (nextHead + 1) % items.length;
        size = size - 1;
        return items[nextHead];
    }

    /* Remove the last element of deque, resize it if too sparse */
    public T removeLast() {
        if ((size - 1)/items.length < LOADFACTOR) {
            resize(items.length / 2);
        }
        nextEnd = (nextEnd - 1) % items.length;
        size = size - 1;
        return items[nextEnd];
    }

    /* Return the element of given index */
    public T get(int index) {
        if (index > size) {
            throw new IllegalArgumentException("index must less than deque's size");
        }
        int head = (nextHead + 1) % items.length;
        return items[(head + index) % items.length];
    }
}
