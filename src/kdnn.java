import java.io.*;
import java.util.*;

public class Kdnn {

    
    static class Point {
        String name;
        double[] coordinates;

        Point(String name, double[] coordinates) {
            this.name = name;
            this.coordinates = coordinates;
        }
    }


    static class KdNode {
        Point point;
        KdNode left;
         KdNode left;
        

        KdNode(Point point) {
            this.point = point;
            this.left = null;
            this.right = null;
        }
    }

    
    static class KdTree {
        private KdNode root;
        private int k;  

        KdTree(int k) {
            this.k = k;
            this.root = null;
        }

        
        public void buildBalancedTree(List<Point> points) {
            root = buildBalancedRec(points, 0);
        }

        private KdNode buildBalancedRec(List<Point> points, int depth) {
            if (points.isEmpty()) return null;

            
            int cd = depth % k;
            points.sort(Comparator.comparingDouble(p -> p.coordinates[cd]));

            int medianIndex = points.size() / 2;
            Point medianPoint = points.get(medianIndex);
            KdNode node = new KdNode(medianPoint);

            
            node.left = buildBalancedRec(points.subList(0, medianIndex), depth + 1);
            node.right = buildBalancedRec(points.subList(medianIndex + 1, points.size()), depth + 1);

            return node;
        }

        
        public double euclideanDistance(Point p1, Point p2) {
            double sum = 0;
            for (int i = 0; i < p1.coordinates.length; i++) {
                sum += Math.pow(p1.coordinates[i] - p2.coordinates[i], 2);
            }
            return Math.sqrt(sum);
        }

        
        public Point findNearestNeighbor(Point testPoint) {
            return findNearestNeighborRec(root, testPoint, 0, null, Double.MAX_VALUE);
        }

        private Point findNearestNeighborRec(KdNode node, Point testPoint, int depth, Point bestPoint, double bestDist) {
            if (node == null) return bestPoint;

            double dist = euclideanDistance(testPoint, node.point);

            
            if (dist < bestDist) {
                bestDist = dist;
                bestPoint = node.point;
            }

            int cd = depth % k;
            KdNode nextNode = null, otherNode = null;


            if (testPoint.coordinates[cd] < node.point.coordinates[cd]) {
                nextNode = node.left;
                otherNode = node.right;
            } else {
                nextNode = node.right;
                otherNode = node.left;
            }

            
            bestPoint = findNearestNeighborRec(nextNode, testPoint, depth + 1, bestPoint, bestDist);
            bestDist = euclideanDistance(testPoint, bestPoint);

            
            if (Math.abs(testPoint.coordinates[cd] - node.point.coordinates[cd]) < bestDist) {
                bestPoint = findNearestNeighborRec(otherNode, testPoint, depth + 1, bestPoint, bestDist);
            }

            return bestPoint;
        }

        
        public int getHeight() {
            return getHeightRec(root);
        }

        private int getHeightRec(KdNode node) {
            if (node == null) return 0;
            int leftHeight = getHeightRec(node.left);
            int rightHeight = getHeightRec(node.right);
            return Math.max(leftHeight, rightHeight) + 1;
        }

        
        
        public int getMinimalHeight(int n) {
            return (int) Math.ceil(Math.log(n) / Math.log(2));
        }

        
        public void printTree() {
            printTreeRec(root, 0);
        }

        private void printTreeRec(KdNode node, int level) {
            if (node == null) return;

            printTreeRec(node.left, level + 1);

            
            for (int i = 0; i < level; i++) System.out.print("    ");
            System.out.printf("%s [%.1f, %.1f]\n", node.point.name, node.point.coordinates[0], node.point.coordinates[1]);

            printTreeRec(node.right, level + 1);
        }
    }

    public static void main(String[] args) throws IOException {
        if (args.length != 1) {
            System.out.println("Usage: java Kdnn <data-file>");
            return;
        }

        String dataFile = args[0];
        List<Point> points = new ArrayList<>();

        
        BufferedReader reader = new BufferedReader(new FileReader(dataFile));
        String firstLine = reader.readLine();
        String[] dimensions = firstLine.split(" ");
        int numPoints = Integer.parseInt(dimensions[0]);
        int dim = Integer.parseInt(dimensions[1]);

        String line;
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(" ");
            String name = parts[0];
            double[] coordinates = new double[dim];
            for (int i = 1; i < parts.length; i++) {
                coordinates[i - 1] = Double.parseDouble(parts[i]);
            }
            points.add(new Point(name, coordinates));
        }

        
        KdTree kdTree = new KdTree(dim);
        kdTree.buildBalancedTree(points);

        
        int height = kdTree.getHeight();
        System.out.println("Height of the balanced k-d tree: " + height);

        int minimalHeight = kdTree.getMinimalHeight(numPoints);
        System.out.println("Minimal height for " + numPoints + " points: " + minimalHeight);

        
        System.out.println("\nKdTree Structure:");
        kdTree.printTree();

        
        BufferedReader testReader = new BufferedReader(new InputStreamReader(System.in));
        while ((line = testReader.readLine()) != null) {
            String[] parts = line.split(" ");
            String name = parts[0];
            double[] coordinates = new double[dim];
            for (int i = 1; i < parts.length; i++) {
                coordinates[i - 1] = Double.parseDouble(parts[i]);
            }
            Point testPoint = new Point(name, coordinates);

            
            Point nearestNeighbor = kdTree.findNearestNeighbor(testPoint);
            double dist = kdTree.euclideanDistance(testPoint, nearestNeighbor);

            
            System.out.printf("Nearest neighbor to [%.1f, %.1f]is : %s [%.1f, %.1f] dist = %.6f\n",
                    testPoint.coordinates[0], testPoint.coordinates[1],
                    nearestNeighbor.name,
                    nearestNeighbor.coordinates[0], nearestNeighbor.coordinates[1], dist);
        }
    }
}
