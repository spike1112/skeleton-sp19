package bearmaps;

import java.util.Comparator;
import java.util.List;

public class KDTree implements PointSet{

    private TreeNode root;


    private static class TreeNode {
        private final Point point;
        private TreeNode leftChild;
        private TreeNode rightChild;
        Comparator<Point> comparator;

        public TreeNode(Point point, Comparator<Point> comparator) {
            this.point = point;
            this.comparator = comparator;
        }
    }

    Comparator<Point> xComparator = Comparator.comparingDouble(Point::getX);
    Comparator<Point> yComparator = Comparator.comparingDouble(Point::getY);
    Boolean compareX = true;

    public KDTree(List<Point> points) {

        for (Point point : points) {
            root = insert(root, point);
        }
    }

    private TreeNode insert(TreeNode node, Point p) {
        if (node == null) {
            if (compareX) {


                compareX = false;
                return new TreeNode(p, xComparator);
                /*
                TreeNode newNode = new TreeNode(p, xComparator);
                compareX = false;
                return newNode; */
            }

            compareX = true;
            return new TreeNode(p, yComparator);
           /*
            TreeNode newNode = new TreeNode(p, yComparator);
            compareX = true;
            return newNode; */
        }
        int cmp = node.comparator.compare(p, node.point);
        if (cmp < 0) {
            node.leftChild = insert(node.leftChild, p);
        } else {
            node.rightChild = insert(node.rightChild, p);
        }
        return node;
    }

    @Override
    public Point nearest(double x, double y) {
        //return naiveNearest(root, new Point(x, y), root).point;
        return nearest(root, new Point(x, y), root).point;
    }

    private TreeNode nearest(TreeNode n, Point goal, TreeNode best) {
        if (n == null) {
            return best;
        }
        if (Point.distance(n.point, goal) < Point.distance(best.point, goal)) {
            best = n;
        }
        TreeNode goodSide, badSide;
        if (n.comparator.compare(goal, n.point) < 0 ) {
            goodSide = n.leftChild;
            badSide = n.rightChild;
        } else {
            goodSide = n.rightChild;
            badSide = n.leftChild;
        }
        best = nearest(goodSide, goal, best);

        double distance = Math.sqrt(Point.distance(best.point, goal));

        double xDistance = Math.abs((goal.getX() - n.point.getX()));
        double yDistance = Math.abs((goal.getY() - n.point.getY()));
        if ((n.comparator.equals(xComparator) && xDistance < distance) ||
                (n.comparator.equals(yComparator) && yDistance < distance)) {
            best = nearest(badSide, goal, best);
        }
        return best;
    }

    private TreeNode naiveNearest(TreeNode n, Point goal, TreeNode best) {
        if (n == null) {
            return best;
        }
        if (Point.distance(n.point, goal) < Point.distance(best.point, goal)) {
            best = n;
        }
        best = naiveNearest(n.leftChild, goal, best);
        best = naiveNearest(n.rightChild, goal, best);
        return best;
    }
}
