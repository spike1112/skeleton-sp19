import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class MyHashMap<K, V> implements Map61B<K, V>{

    private Node<K, V>[] table;
    private final HashSet<K> keys;

    private int initialSize = 16;
    private double loadFactor = 0.75;

    private static class Node<K, V> {
        private K key;
        private V value;
        private Node<K, V> next;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
        public Node() {
        }
    }

    public MyHashMap() {
        this(16, 0.75);
    }

    public MyHashMap(int initialSize) {

        //table = (Node<K, V>[]) new Object[initialSize];
        this(initialSize, 0.75);
    }

    public MyHashMap(int initialSize, double loadFactor) {
        this.initialSize = initialSize;
        this.loadFactor = loadFactor;
        keys = new HashSet<>();
        table = (Node<K, V>[]) new Node[initialSize];
    }

    @Override
    public void clear() {
        Arrays.fill(table, null);
        keys.clear();
    }

    @Override
    public boolean containsKey(K key) {
        if (key == null) {
            throw new IllegalArgumentException("null key");
        }
        return keys.contains(key);
    }

    @Override
    public V get(K key) {
        if (key == null) {
            throw new IllegalArgumentException("null key");
        }
        int index = Math.floorMod(key.hashCode(), table.length);
        Node<K, V> p = table[index];

        while (true) {
            if (p == null) {
                return null;
            }
            if (p.key.equals(key)) {
                return p.value;
            }
            p = p.next;
        }
    }

    @Override
    public int size() {
        return keys.size();
    }

    @Override
    public void put(K key, V value) {
        if ((size() + 1.0) / table.length >= loadFactor) {
            resize();
        }
        keys.add(key);
        int index = Math.floorMod(key.hashCode(), table.length);
        Node<K, V> p = table[index];
        if (p == null) {
            table[index] = new Node<>(key, value);
            return;
        }

        while (true) {
            if (p.key.equals(key)) {
                p.value = value;
                break;
            }
            if (p.next == null) {
                p.next = new Node<>(key, value);
                break;
            }
            p = p.next;
        }
    }

    private void resize() {
        MyHashMap<K, V> newMap = new MyHashMap<>(2 * initialSize);
        for (K k : keys) {
            newMap.put(k, this.get(k));
        }
        table = newMap.table;
        initialSize = newMap.initialSize;
    }

    @Override
    public Set<K> keySet() {
        return keys;
    }

    @Override
    public V remove(K key) {
        throw new UnsupportedOperationException();
    }

    @Override
    public V remove(K key, V value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterator<K> iterator() {
        return keys.iterator();
    }
}
