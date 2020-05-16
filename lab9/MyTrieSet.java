import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyTrieSet implements TrieSet61B {

    private Node root;

    private static class Node {
        private boolean isKey;
        private final Map<Character, Node> next;

        private Node(boolean isKey) {
            this.isKey = isKey;
            next = new HashMap<>();
        }
    }

    public MyTrieSet() {
        root = new Node(false);
       
    }

    @Override
    public void clear() {
        root = null;
    }

    @Override
    public boolean contains(String key) {
        if (key == null || key.length() == 0) {
            throw new IllegalArgumentException("key is empty or null");
        }

        Node curr = root;
        for (int i = 0, n = key.length(); i < n; i++) {
            if (curr == null) {
                return false;
            }
            curr = curr.next.get(key.charAt(i));
        }
        return curr.isKey;
    }



    @Override
    public void add(String key) {
        if (key == null || key.length() < 1) {
            return;
        }
        Node curr = root;
        for (int i = 0, n = key.length(); i < n; i++) {
            char c = key.charAt(i);
            if (!curr.next.containsKey(c)) {
                curr.next.put(c, new Node(false));
            }
            curr = curr.next.get(c);
        }

        curr.isKey = true;
    }


    @Override
    public List<String> keysWithPrefix(String prefix) {

        List<String> x = new ArrayList<>();
        Node curr = root;
        for (int i = 0, n = prefix.length(); i < n; i++) {
            char c = prefix.charAt(i);
            curr = curr.next.get(c);
        }

        for (char c : curr.next.keySet()) {
            colHelp(prefix + c, x, curr.next.get(c));
        }
        return x;
    }


    private void colHelp(String s, List<String> x, Node n) {
        if (n == null) {
            return;
        }
        if (n.isKey) {
            x.add(s);
        }
        for (char c : n.next.keySet()) {
           colHelp(s + c, x, n.next.get(c));
        }
    }

    @Override
    public String longestPrefixOf(String key) {
        throw new UnsupportedOperationException();
    }

}
