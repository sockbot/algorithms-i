import java.util.Arrays;

public class BruteCollinearPoints {

    private LineSegment[] segments;

    // finds all line segments containing 4 points
    public BruteCollinearPoints(Point[] points) {
        if (points == null)
            throw new IllegalArgumentException();

        Arrays.sort(points);

        for (int i = 0; i < points.length; i++) {
            if (points[i] == null || (i > 0 && points[i].equals(points[i - 1])))
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
