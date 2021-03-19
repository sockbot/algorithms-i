import java.util.ArrayList;
import java.util.Arrays;

public class FastCollinearPoints {

    private final ArrayList<LineSegment> segments = new ArrayList<>();

    public FastCollinearPoints(Point[] points) {
        if (points == null)
            throw new IllegalArgumentException();

        Point[] pointsCopy = Arrays.copyOf(points, points.length);
        Arrays.sort(pointsCopy);

        for (int i = 0; i < pointsCopy.length; i++) {
            if (pointsCopy[i] == null || (i > 0 && pointsCopy[i].compareTo(pointsCopy[i - 1]) == 0))
                throw new IllegalArgumentException();
        }

        /**
         * Think of p as the origin.
         * For each other point q, determine the slope it makes with p.
         * Sort the points according to the slopes they makes with p.
         * Check if any 3 (or more) adjacent points in the sorted order have equal slopes with respect to p. If so, these points, together with p, are collinear.
         */
        for (int p = 0; p < pointsCopy.length; p++) {
            for (int q = 0; q < pointsCopy.length; q++) {
                if (p == q)
                    continue;
            }
        }
    }

    public int numberOfSegments() {
        return this.segments.size();
    }

    public LineSegment[] segments() {
        return this.segments.toArray(new LineSegment[this.numberOfSegments()]);
    }
}
