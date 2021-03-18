import java.util.Arrays;

public class BruteCollinearPoints {

    private int segmentsCount = 0;
    private LineSegment[] lineSegments;

    // finds all line segments containing 4 points
    public BruteCollinearPoints(Point[] points) {
        if (points == null)
            throw new IllegalArgumentException();

        this.lineSegments = new LineSegment[points.length];
        Arrays.sort(points);

        for (int i = 0; i < points.length; i++) {
            if (points[i] == null || (i > 0 && points[i].equals(points[i - 1])))
                throw new IllegalArgumentException();
        }

        for (int p = 0; p < points.length - 3; p++) {
            for (int q = p + 1; q < points.length - 2; q++) {
                for (int r = q + 1; r < points.length - 1; r++) {
                    for (int s = r + 1; s < points.length; s++) {
                        if (points[p].slopeTo(points[q]) == points[p].slopeTo(points[r])
                                && points[p].slopeTo(points[r]) == points[p].slopeTo(points[s])) {
                            // System.out.printf("%s %s %s %s",
                            //                   points[p].toString(),
                            //                   points[q].toString(),
                            //                   points[r].toString(),
                            //                   points[s].toString()
                            // );
                            this.lineSegments[this.segmentsCount++] = new LineSegment(points[p],
                                                                                      points[s]);
                        }
                    }
                }
            }
        }
    }

    // the number of line segments
    public int numberOfSegments() {
        return this.segmentsCount;
    }

    // the line segments
    public LineSegment[] segments() {
        return this.lineSegments;
    }

    public static void main(String[] args) {
    }
}
