package bearmaps;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.List;

public class NaivePointSet implements PointSet{

    private List<Point> points;

    public NaivePointSet(List<Point> points) {
        this.points = points;
    }

    @Override
    public Point nearest(double x, double y) {
        Point point = new Point(x, y);
        Point result = null;
        double min = Double.MAX_VALUE;
        for (Point p : points) {
            double distance = Point.distance(point, p);
            if (distance <= min) {
                min = distance;
                result = p;
            }
        }
        return result;
    }
}
