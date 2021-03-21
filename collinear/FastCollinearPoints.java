import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class FastCollinearPoints {

    private ArrayList<LineSegment> segments = new ArrayList<>();
    private final Point[] sortedPoints;

    public FastCollinearPoints(Point[] points) {
        if (points == null)
            throw new IllegalArgumentException();

        this.sortedPoints = Arrays.copyOf(points, points.length);
        Arrays.sort(this.sortedPoints);

        for (int i = 0; i < sortedPoints.length; i++) {
            if (sortedPoints[i] == null || (i > 0
                    && sortedPoints[i].compareTo(sortedPoints[i - 1]) == 0))
                throw new IllegalArgumentException();
        }

        this.findSegments();
    }

    private void findSegments() {
        /**
         * Think of p as the origin.
         * For each other point q, determine the slope it makes with p.
         * Sort the points according to the slopes they makes with p.
         * Check if any 3 (or more) adjacent points in the sorted order have equal slopes with respect to p. If so, these points, together with p, are collinear.
         */
        for (int p = 0; p < this.sortedPoints.length; p++) {
            final Point point = this.sortedPoints[p];
            final List<Point> pointsSortedBySlope = Arrays.stream(this.sortedPoints)
                                                          .filter(x -> !x.equals(point))
                                                          .sorted(point.slopeOrder())
                                                          .collect(Collectors.toList());
            double currSlope = Double.POSITIVE_INFINITY;
            int pointCount = 1;

            // create a container hold a sublist
            for (int q = 0; q < pointsSortedBySlope.size(); q++) {
                final double slope = point.slopeTo(pointsSortedBySlope.get(q));
                if (slope == currSlope) {
                    pointCount++;
                }
                else if (pointCount >= 3) {
                    List<Point> sublist = new ArrayList<>(
                            pointsSortedBySlope.subList(q - pointCount, q));
                    sublist.add(point);
                    Collections.sort(sublist);
                    this.segments
                            .add(new LineSegment(sublist.get(0), sublist.get(sublist.size() - 1)));
                }
                else {
                    currSlope = slope;
                    pointCount = 1;
                }

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
