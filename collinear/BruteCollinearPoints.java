import java.util.HashSet;
import java.util.Set;

public class BruteCollinearPoints {

    private LineSegment[] segments;

    // finds all line segments containing 4 points
    public BruteCollinearPoints(Point[] points) {
        if (points == null)
            throw new IllegalArgumentException();

        Set<Point> set = new HashSet<Point>();

        for (int i = 0; i < points.length; i++) {
            if (points[i] == null || !set.add(points[i]))
                throw new IllegalArgumentException();
        }
    }

    // the number of line segments
    public int numberOfSegments() {
        return this.segments.length + 1;
    }

    // the line segments
    public LineSegment[] segments() {
        return this.segments;
    }
}
