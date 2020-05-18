package bearmaps;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.junit.Assert.assertEquals;

public class KDTreeTest {

    @Test
    public void testNaivePointSet() {

        Point p1 = new Point(1.1, 2.2);
        Point p2 = new Point(3.3, 4.4);
        Point p3 = new Point(-2.9, 4.2);

        NaivePointSet points = new NaivePointSet(List.of(p1, p2, p3));

        Point ret = points.nearest(3.0, 4.0);

        assertEquals(3.3, ret.getX(), 0.0);
        assertEquals(4.4, ret.getY(), 0.0);
    }

    @Test
    public void testNaiveKDTree() {
        Point p1 = new Point(1.1, 2.2);
        Point p2 = new Point(3.3, 4.4);
        Point p3 = new Point(-2.9, 4.2);

        KDTree kdTree = new KDTree(List.of(p1, p2, p3));

        Point ret = kdTree.nearest(3.0, 4.0);
        assertEquals(3.3, ret.getX(), 0.0);
        assertEquals(4.4, ret.getY(), 0.0);
    }

    //randomized test
    @Test
    public void testKDTree() {

        Random random = new Random();

        List<Point> points = new ArrayList<>();
        List<Point> targetPoints = new ArrayList<>();

        for (int i = 0; i < 1000; i++) {
            points.add(new Point(random.nextDouble(), random.nextDouble()));
        }

        for (int i = 0; i < 999; i++) {
            targetPoints.add(new Point(random.nextDouble(), random.nextDouble()));
        }

        NaivePointSet naivePointSet = new NaivePointSet(points);
        KDTree kdTree = new KDTree(points);

        for (Point p : targetPoints) {
            assertEquals(naivePointSet.nearest(p.getX(), p.getY()),
                    kdTree.nearest(p.getX(), p.getY()));
        }
    }

    @Test
    public void simpleKDTreeTest() {
        Point p1 = new Point(0.6, 38.6);
        Point p2 = new Point(0.2, 38.2);
        Point p3 = new Point(0.5, 38.5);
        Point p4 = new Point(0.4, 38.4);
        Point p5 = new Point(0.1, 38.1);
        Point p6 = new Point(0.4, 38.6);
        Point p7 = new Point(0.6, 38.3);
        List<Point> points = List.of(p1, p2, p3, p4, p5, p6, p7);
        KDTree kd = new KDTree(points);
        Point p = kd.nearest(0.1, 38.1);
        assertEquals(p.getX(), 0.1, 0.0);
        assertEquals(p.getY(), 38.1, 0.0);

    }

}
