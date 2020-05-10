import org.junit.Test;
import static org.junit.Assert.*;

public class TestDeque {


    /** test LinkedListDeque */
    @Test
    public void testLinkedListDeque() {
        LinkedListDeque<Integer> deque = new LinkedListDeque<>();
        assertTrue(deque.isEmpty());
        deque.addFirst(5);
        deque.addFirst(9);
        deque.addLast(2);
        deque.addLast(7);
        assertEquals(4, deque.size());
        assertEquals(9, (float)deque.get(0), 0.0);
        assertEquals(2, (float)deque.get(2), 0.0);
        deque.addLast(45);
        deque.removeLast();
        assertEquals(2, (float)deque.get(2), 0.0);
        deque.addFirst(23);
        deque.removeFirst();
        assertEquals(5, (float)deque.get(1), 0.0);
        assertFalse(deque.isEmpty());
        LinkedListDeque<Integer> otherDeque = new LinkedListDeque<>(deque);
        assertEquals(5, (float)deque.get(1), 0.0);
    }

    /** test ArrayDeque */
    @Test
    public void testArrayDeque() {
        ArrayDeque<String> deque = new ArrayDeque<>();
        assertTrue(deque.isEmpty());
        deque.addFirst("hello");
        deque.addFirst("world");
        deque.addLast("jinx");
        deque.addLast("jax");
        deque.addFirst("flag");
        assertEquals(5, deque.size());
        assertEquals("world", deque.get(1));
        assertEquals("jinx", deque.get(3));
        deque.addFirst("la");
        deque.removeFirst();
        assertEquals("world", deque.get(1));
        deque.addLast("god");
        deque.removeLast();
        assertEquals("hello", deque.get(2));
        assertFalse(deque.isEmpty());
        ArrayDeque<Integer> otherDeque = new ArrayDeque<>(deque);
        assertEquals("hello", deque.get(2));

    }
}
