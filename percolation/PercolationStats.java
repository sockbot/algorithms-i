/* *****************************************************************************
 *  Name: Andrew Chan
 *  Date: 15 Apr 2020
 *  Description: Princeton Algorithms I, Week 1
 **************************************************************************** */

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {

    private static final double CONFIDENCE_95 = 1.96;
    private final double[] results;
    private final int trials;

    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials) {
        this.trials = trials;
        if (n <= 0 || trials <= 0)
            throw new IllegalArgumentException();
        this.results = new double[trials];
        for (int i = 0; i < trials; i++) {
            Percolation percolation = new Percolation(n);
            while (!percolation.percolates()) {
                int randomRow = StdRandom.uniform(n) + 1;
                int randomCol = StdRandom.uniform(n) + 1;
                percolation.open(randomRow, randomCol);
            }
            this.results[i] = (double) percolation.numberOfOpenSites() / (double) (n * n);
        }
    }

    // sample mean of percolation threshold
    public double mean() {
        return StdStats.mean(this.results);
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return StdStats.stddev(this.results);
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo() {
        return this.mean() - ((CONFIDENCE_95 * this.stddev()) / Math.sqrt(this.trials));
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return this.mean() + ((CONFIDENCE_95 * this.stddev()) / Math.sqrt(this.trials));
    }

    // test client (see below)
    public static void main(String[] args) {
        PercolationStats ps = new PercolationStats(Integer.parseInt(args[0]),
                                                   Integer.parseInt(args[1]));
        System.out.printf("mean\t\t\t\t\t\t=\t%f\n", ps.mean());
        System.out.printf("stddev\t\t\t\t\t\t=\t%f\n", ps.stddev());
        System.out.printf("95%% confidence interval\t\t=\t[%f, %f]\n", ps.confidenceLo(),
                          ps.confidenceHi());
    }
}
