package bearmaps.proj2ab;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ArrayHeapMinPQ<T> implements ExtrinsicMinPQ<T> {

    private ArrayList<Node<T>> heap;
    private Map<T, Integer> map;

    private static class Node<T> implements Comparable<Node<T>> {
        T item;
        double priority;

        private Node(T i, double p) {
            item = i;
            priority = p;
        }

        @Override
        public int compareTo(Node other) {
            return Double.compare(priority, other.priority);
        }

        public T getItem() {
            return item;
        }

        public double getPriority() {
            return priority;
        }

        public void setItem(T item) {
            this.item = item;
        }

        public void setPriority(double priority) {
            this.priority = priority;
        }
    }

    public ArrayHeapMinPQ() {
        heap = new ArrayList<>();
        heap.add(null);
        map = new HashMap<>();
    }



    @Override
    public void add(T item, double priority) {
        if (map.containsKey(item)) {
            throw new IllegalArgumentException();
        }

        heap.add(new Node<>(item, priority));
        map.put(item, size());
        swim(size());

    }

    private void swim(int k) {
        while (k > 1 && greater(parent(k), k)) {
            swap(k, parent(k));
            k = k / 2;
        }
    }

    private boolean greater(int i, int j) {
        return heap.get(i).compareTo(heap.get(j)) > 0;
    }

    private void swap(int i, int j) {
        T tempT = heap.get(i).getItem();
        double tempP = heap.get(i).getPriority();
        Node<T> node1 = heap.get(i);
        Node<T> node2 = heap.get(j);
        node1.setItem(node2.getItem());
        node1.setPriority(node2.getPriority());
        node2.setItem(tempT);
        node2.setPriority(tempP);
        map.put(node1.getItem(), i);
        map.put(node2.getItem(), j);
    }

    private int parent(int k)  {
        return k / 2;
    }

    @Override
    public boolean contains(T item) {
        return map.containsKey(item);
    }

    @Override
    public T getSmallest() {
        return heap.get(1).item;
    }

    @Override
    public T removeSmallest() {
        swap(1, size());
        Node<T> removed = heap.remove(size());
        sink(1);
        return removed.getItem();
    }

    void sink(int k) {
        while (2 * k <= size()) {
            int j = 2 * k;
            if (j < size() && greater(j, j + 1)) {
                j = j + 1;
            }
            if (!greater(k, j)) {
                break;
            }
            swap(k, j);
            k = j;
        }
    }

    @Override
    public int size() {
        return heap.size() - 1;
    }

    @Override
    public void changePriority(T item, double priority) {
        int index= map.get(item);
        heap.get(index).setPriority(priority);
        sink(index);
        swim(index);
    }
}
