/* *****************************************************************************
 *  Name: Andrew Chan
 *  Date: 15 Apr 2020
 *  Description: Princeton Algorithms I, Week 1
 **************************************************************************** */

public class PercolationStats {

    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials) {
        if (n <= 0 || trials <= 0)
            throw new Exception();

    }

    // sample mean of percolation threshold
    public double mean()

    // sample standard deviation of percolation threshold
    public double stddev()

    // low endpoint of 95% confidence interval
    public double confidenceLo()

    // high endpoint of 95% confidence interval
    public double confidenceHi()

    // test client (see below)
    public static void main(String[] args) {

    }
}
