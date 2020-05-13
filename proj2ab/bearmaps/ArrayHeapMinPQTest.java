package bearmaps;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ArrayHeapMinPQTest {

    //simple test for my ArrayHeapMinPQTest
    @Test
    public void testMinPQ() {
        ArrayHeapMinPQ<Integer> heap = new ArrayHeapMinPQ<>();
        heap.add(23, 1.05);
        heap.add(45, 0.05);
        heap.add(1, 13.0);
        heap.add(65, 3.4);
        assertEquals(45, heap.getSmallest(), 0);
        heap.removeSmallest();
        assertEquals(23, heap.getSmallest(), 0);
        assertEquals(3, heap.size());
        heap.changePriority(65, 0.001);
        heap.add(34, 3.5);
        heap.add(56, 0.3);
        assertTrue(heap.contains(34));
        assertEquals(65, heap.getSmallest(), 0);
    }
}
