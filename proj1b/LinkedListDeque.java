public class LinkedListDeque<T> implements Deque<T>{

    private final Node sentinel;
    private int size;

    private class Node {

        private final T item;
        private Node back;
        private Node next;

        public Node(T item, Node back, Node next) {
            this.item = item;
            this.back = back;
            this.next = next;
        }
    }

    public LinkedListDeque() {
        sentinel = new Node(null, null, null);
        sentinel.back = sentinel;
        sentinel.next =sentinel;
        size = 0;
    }

    public LinkedListDeque(LinkedListDeque<T> other) {
        LinkedListDeque<T> deque = new LinkedListDeque<>();
        Node p = other.sentinel.next;
        while (p.next != other.sentinel) {
            deque.addLast(p.item);
            p = p.next;
        }

        size = deque.size;
        sentinel = deque.sentinel;
    }

    /** Adds a type T item to the front of the deque
     *
     * @param item added
     */
    @Override
    public void addFirst(T item) {
        Node newNode = new Node(item, sentinel, sentinel.next);
        sentinel.next.back = newNode;
        sentinel.next = newNode;
        size = size + 1;
    }

    /** Add a type T item to the end of deque
     *
     * @param item be added
     */
    @Override
    public void addLast(T item) {
        Node newNode = new Node(item, sentinel.back, sentinel);
        sentinel.back.next = newNode;
        sentinel.back = newNode;
        size = size + 1;
    }

    /** Return the size of the deque
     *
     * @return size of the deque
     */
    @Override
    public int size() {
        return size;
    }

    /** Print the whole deque from head to end */
    @Override
    public void printDeque() {
        Node p = sentinel.next;
        while (p.next != sentinel) {
            System.out.print(p.item);
            p = p.next;
        }
    }

    /** Remove and return the first item of the deque
     *
      * @return the first item
     */
    @Override
    public T removeFirst() {
        T item = sentinel.next.item;
        sentinel.next.next.back = sentinel;
        sentinel.next = sentinel.next.next;
        size = size - 1;

        return item;
    }
    /** Remove and return the last item of deque
     *
     * @return last item
     */
    @Override
    public T removeLast() {

        T item = sentinel.back.item;
        sentinel.back.back.next = sentinel;
        sentinel.back = sentinel.back.back;
        size = size - 1;

        return item;
    }

    /** retrieve the item at the given index
     *
     * @param index location in the deque
     * @return the item at the given item
     */
    @Override
    public T get(int index) {

        Node p = sentinel.next;
        int i = 0;
        while (p.next != sentinel && i != index) {
            p = p.next;
            i = i + 1;
        }

        return p.item;
    }

    public T getRecursive(int index) {
        return getRecursive(sentinel, index);
    }

    private T getRecursive(Node node, int i) {
        if (i == 0) {
            return node.item;
        }
        return getRecursive(node.next, i - 1);
    }


}
