import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class UnionFindTest {


    @Test
    public void unionFindTest() {

        UnionFind unionFind = new UnionFind(16);
        unionFind.union(0, 1);
        unionFind.union(4, 7);
        unionFind.union(2, 5);
        unionFind.union(2, 7);
        assertTrue(unionFind.connected(4, 5));
        assertFalse(unionFind.connected(1, 7));

        unionFind.union(5, 6);
        unionFind.union(5, 9);
        unionFind.union(2, 8);
        unionFind.union(3, 4);
        unionFind.union(1, 6);
        unionFind.union(10, 12);
        unionFind.union(14, 12);
        unionFind.union(13, 15);

        assertTrue(unionFind.connected(1, 3));
        assertFalse(unionFind.connected(1, 13));
        assertTrue(unionFind.connected(12, 14));
        assertTrue(unionFind.connected(13, 15));
        assertTrue(unionFind.connected(12, 12));
       

    }

}
