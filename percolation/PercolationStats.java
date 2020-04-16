/* *****************************************************************************
 *  Name: Andrew Chan
 *  Date: 15 Apr 2020
 *  Description: Princeton Algorithms I, Week 1
 **************************************************************************** */

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {

    private int results[];
    private int trials;

    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials) {
        this.trials = trials;
        if (n <= 0 || trials <= 0)
            throw new IllegalArgumentException();
        Percolation[] percolations = new Percolation[trials];
        this.results = new int[trials];
        for (int i = 0; i < trials; i++) {
            percolations[i] = new Percolation(n);
            while (!percolations[i].percolates()) {
                int randomRow = StdRandom.uniform(n - 1) + 1;
                int randomCol = StdRandom.uniform(n - 1) + 1;
                percolations[i].open(randomRow, randomCol);
            }
            this.results[i] = percolations[i].numberOfOpenSites() / (n * n);
        }
    }

    // sample mean of percolation threshold
    public double mean() {
        return StdStats.mean(results);
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return StdStats.stddev(results);
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo() {
        return this.mean() - ((1.96 * this.stddev()) / Math.sqrt(this.trials));
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return this.mean() + ((1.96 * this.stddev()) / Math.sqrt(this.trials));
    }

    // test client (see below)
    public static void main(String[] args) throws Exception {
        System.out.print("Hello World");
        PercolationStats ps = new PercolationStats(Integer.parseInt(args[0]),
                                                   Integer.parseInt(args[1]));
        System.out.printf("mean\t\t\t=\t%f", ps.mean());
        System.out.printf("stddev\t\t\t=\t%f", ps.stddev());
        System.out.printf("95%% confidence interval\t\t=\t[%f, %f]", ps.confidenceLo(),
                          ps.confidenceHi());
    }
}
