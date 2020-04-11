/* *****************************************************************************
 *  Name: Andrew Chan
 *  Date: 10 Apr 2020
 *  Description: Princeton Algorithms I, Week 1
 **************************************************************************** */

public class Percolation {

    private boolean[][] grid;
    private int numOpenSites;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        // make grid 1 index bigger so we can reference indexes 1 - n
        grid = new boolean[n + 1][n + 1];
        numOpenSites = 0;

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                grid[i][j] = false;
            }
        }
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        if (row > grid[0].length || col > grid[0].length) {
            throw new IllegalArgumentException("Row or Col out of bounds");
        }

        grid[row][col] = true;
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        return grid[row][col];
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
        Percolation p = new Percolation(9);
    }
}
