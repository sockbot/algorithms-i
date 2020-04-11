/* *****************************************************************************
 *  Name: Andrew Chan
 *  Date: 10 Apr 2020
 *  Description: Princeton Algorithms I, Week 1
 **************************************************************************** */

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private boolean[][] grid;
    private int numOpenSites;
    private WeightedQuickUnionUF ufMap;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        grid = new boolean[n][n];
        numOpenSites = 0;

        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {
                grid[row][col] = false;
            }
        }

        ufMap = new WeightedQuickUnionUF(n);
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        if (this.validateArgs(row - 1, col - 1))
            grid[row - 1][col - 1] = true;

        // link open sites to open neighbours
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        return grid[row - 1][col - 1];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        return false;
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return numOpenSites;
    }

    // does the system percolate?
    public boolean percolates() {
        return false;
    }

    public static void main(String[] args) {
        Percolation p = new Percolation(5);
        System.out.println(p.xyTo1D(0, 0));
        System.out.println(p.xyTo1D(0, 1));
        System.out.println(p.xyTo1D(0, 2));
        System.out.println(p.xyTo1D(0, 3));
        System.out.println(p.xyTo1D(0, 4));
        System.out.println(p.xyTo1D(1, 0));
        System.out.println(p.xyTo1D(1, 1));
        System.out.println(p.xyTo1D(1, 2));
        System.out.println(p.xyTo1D(1, 3));
        System.out.println(p.xyTo1D(1, 4));
        System.out.println(p.xyTo1D(2, 0));
        System.out.println(p.xyTo1D(2, 1));
        System.out.println(p.xyTo1D(2, 2));

    }

    /**
     * Map x and y coordinates into a 1 dimensional array
     *
     * @param row
     * @param col
     * @return int
     */
    private int xyTo1D(int row, int col) {
        if (this.validateArgs(row, col))
            return (grid[0].length * row) + col;
        return -1;
    }

    /**
     * Check if arguments are valid
     */
    private boolean validateArgs(int row, int col) {
        if (row > this.grid[0].length || col > this.grid[0].length) {
            throw new IllegalArgumentException("Row or Col out of bounds");
        }
        return true;
    }
}
