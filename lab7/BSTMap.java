import java.util.*;

public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V> {

    private TreeNode root;

    private class TreeNode {
        K key;
        V value;
        TreeNode left;
        TreeNode right;
        int size;

        public TreeNode(K key, V value, int size) {
            this.key = key;
            this.value = value;
            this.size = size;
        }
        public TreeNode() {

        }
    }

    @Override
    public boolean containsKey(K key) {
        if (key == null) {
            throw new IllegalArgumentException("null key");
        }
        return get(key) != null;
    }


    @Override
    public void clear() {
        root = null;
    }

    @Override
    public V get(K key) {
        return get(key, root);
    }

    private V get(K key, TreeNode node) {
        if (node == null) {
            return null;
        }
        if (key == null) {
            throw new IllegalArgumentException("null key");
        }
        int cmp = key.compareTo(node.key);
        if (cmp == 0) {
            return node.value;
        } else if (cmp < 0) {
            return get(key, node.left);
        } else {
            return get(key, node.right);
        }
    }

    @Override
    public int size() {
        return size(root);
    }


    private int size(TreeNode node) {
        return node == null ? 0 : node.size;
    }

    @Override
    public void put(K key, V value) {
        if (key == null) {
            throw new IllegalArgumentException("key can't be null");
        }
        root = put(key, value, root);
    }

    private TreeNode put(K key, V value, TreeNode node) {
        if (node == null) {
            return new TreeNode(key, value, 1);
        }
        int cmp = key.compareTo(node.key);
        if (cmp < 0) {
            node.left = put(key, value, node.left);
        } else if (cmp > 0) {
            node.right = put(key, value, node.right);
        } else {
            node.value = value;
        }
        node.size = size(node.left) + 1 + size(node.right);
        return node;
    }

    @Override
    public Set<K> keySet() {
        Set<K> keys = new HashSet<>();
        Queue<TreeNode> queue = new LinkedList<>();
        TreeNode p = root;
        while (p != null) {
            keys.add(p.key);

            if (p.left != null) {
                queue.add(p.left);
            }
            if (p.right != null) {
                queue.add(p.right);
            }
            p = queue.poll();
        }
        return keys;
    }

    public V remove(K key) {
        throw new UnsupportedOperationException();
    }

    public V remove(K key, V value) {

        throw new UnsupportedOperationException();
    }

    public void printInOrder() {
        printInOrder(root);
    }

    private void printInOrder(TreeNode node) {
        if (node == null) {
            return;
        }
        printInOrder(node.left);
        System.out.print(node.key);
        printInOrder(node.right);
    }

    @Override
    public Iterator<K> iterator() {
        throw new UnsupportedOperationException();
    }
}
