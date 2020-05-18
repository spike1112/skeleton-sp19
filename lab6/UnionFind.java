import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class UnionFind {

    // TODO - Add instance variables?
    int[] unionFind;

    /* Creates a UnionFind data structure holding n vertices. Initially, all
       vertices are in disjoint sets. */
    public UnionFind(int n) {
        // TODO
        unionFind = new int[n];
        Arrays.fill(unionFind, -1);
    }

    /* Throws an exception if v1 is not a valid index. */
    private void validate(int vertex) {
        // TODO
        if (vertex < 0 || vertex >= unionFind.length) {
            throw new IllegalArgumentException("Invalid index!");
        }
    }

    /* Returns the size of the set v1 belongs to. */
    public int sizeOf(int v1) {
        // TODO
        validate(v1);
        while (unionFind[v1] >= 0) {
            v1 = unionFind[v1];
        }
        return -unionFind[v1];
    }

    /* Returns the parent of v1. If v1 is the root of a tree, returns the
       negative size of the tree for which v1 is the root. */
    public int parent(int v1) {
        // TODO
        validate(v1);
        return unionFind[v1];
    }

    /* Returns true if nodes v1 and v2 are connected. */
    public boolean connected(int v1, int v2) {
        // TODO
        validate(v1);
        validate(v2);
        return find(v1) == find(v2);
    }

    /* Connects two elements v1 and v2 together. v1 and v2 can be any valid 
       elements, and a union-by-size heuristic is used. If the sizes of the sets
       are equal, tie break by connecting v1's root to v2's root. Unioning a 
       vertex with itself or vertices that are already connected should not 
       change the sets but may alter the internal structure of the data. */
    public void union(int v1, int v2) {
        // TODO
        validate(v1);
        validate(v2);
        if (connected(v1, v2)) {
            return;
        }
        int root1 = find(v1);
        int root2 = find(v2);
        if(sizeOf(v1) > sizeOf(v2)) {
            unionFind[root1] += unionFind[root2];
            unionFind[root2] = root1;
        } else {
            unionFind[root2] += unionFind[root1];
            unionFind[root1] = root2;
        }
    }

    /* Returns the root of the set V belongs to. Path-compression is employed
       allowing for fast search-time.
       When search the root of the vertex, store vertices of the path to a set, then set these vertices directly point the root
       */
    public int find(int vertex) {
        // TODO
        validate(vertex);
        Set<Integer> set = new HashSet<>();
        while (parent(vertex) >= 0) {
            set.add(vertex);
            vertex = parent(vertex);
        }

        for (Integer x : set) {
            unionFind[x] = vertex;
        }

        return vertex;
    }

}
