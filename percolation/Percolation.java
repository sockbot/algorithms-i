/* *****************************************************************************
 *  Name: Andrew Chan
 *  Date: 10 Apr 2020
 *  Description: Princeton Algorithms I, Week 1
 **************************************************************************** */

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private static final int UP = 0;
    private static final int RIGHT = 1;
    private static final int DOWN = 2;
    private static final int LEFT = 3;
    private int ufMapTopIndex;
    private int ufMapBottomIndex;
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

        ufMap = new WeightedQuickUnionUF(n * n + 2);
        this.ufMapTopIndex = n * n;
        this.ufMapBottomIndex = n * n + 1;
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        int rowIndex = row - 1;
        int colIndex = col - 1;
        if (this.hasValidArgs(rowIndex, colIndex)) {
            grid[rowIndex][colIndex] = true;
            this.unionNeighbours(rowIndex, colIndex);
        }
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        int rowIndex = row - 1;
        int colIndex = col - 1;
        if (this.hasValidArgs(rowIndex, colIndex)) {
            return grid[rowIndex][colIndex];
        }
        return false;
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        int rowIndex = row - 1;
        int colIndex = col - 1;
        int source = xyTo1D(rowIndex, colIndex);
        return ufMap.find(source) == ufMap.find(ufMapTopIndex);
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return numOpenSites;
    }

    // does the system percolate?
    public boolean percolates() {
        return ufMap.find(ufMapTopIndex) == ufMap.find(ufMapBottomIndex);
    }

    public static void main(String[] args) {
        Percolation p = new Percolation(5);
        // System.out.println(p.xyTo1D(0, 0));
        // System.out.println(p.xyTo1D(0, 1));
        // System.out.println(p.xyTo1D(0, 2));
        // System.out.println(p.xyTo1D(0, 3));
        // System.out.println(p.xyTo1D(0, 4));
        // System.out.println(p.xyTo1D(1, 0));
        // System.out.println(p.xyTo1D(1, 1));
        // System.out.println(p.xyTo1D(1, 2));
        // System.out.println(p.xyTo1D(1, 3));
        // System.out.println(p.xyTo1D(1, 4));
        // System.out.println(p.xyTo1D(2, 0));
        // System.out.println(p.xyTo1D(2, 1));
        // System.out.println(p.xyTo1D(2, 2));
        // System.out.println(p.isOpen(1, 1));
        System.out.println(p.isFull(1, 1));
        p.open(1, 1);
        System.out.println(p.isOpen(1, 1));
        System.out.println(p.isFull(1, 1));

    }

    /**
     * Map x and y coordinates into a 1 dimensional array
     *
     * @param row
     * @param col
     * @return int
     */
    private int xyTo1D(int row, int col) {
        if (row < 0)
            return this.ufMapTopIndex;
        else if (row >= grid[0].length)
            return this.ufMapBottomIndex;
        else if (col < 0)
            return (grid[0].length * row) + col + 1;
        else if (col >= grid[0].length)
            return (grid[0].length * row) + col - 1;
        return (grid[0].length * row) + col;
    }

    /**
     * Check if arguments are valid. Since public API accepts 1-indexed coordinates but privately we
     * are using 0-indexed coordinates, we accept 1-indexed coordinates publicly but validate
     * privately based on 0-index.
     */
    private boolean hasValidArgs(int row, int col) {
        if (row > this.grid[0].length || col > this.grid[0].length)
            return false;
        return true;
    }

    /**
     * Joins a source coord to its open neighbours.
     *
     * @param row
     * @param col
     */
    private void unionNeighbours(int row, int col) {
        int source = this.xyTo1D(row, col);
        int target = 0;
        int targetRowIndex = 0;
        int targetColIndex = 0;
        for (int direction = 0; direction < 4; direction++) {
            switch (direction) {
                case UP:
                    targetRowIndex = row - 1;
                    targetColIndex = col;
                    target = (targetRowIndex < 0) ? ufMapTopIndex :
                             this.xyTo1D(targetRowIndex, targetColIndex);
                    if (targetRowIndex < 0 || grid[targetRowIndex][targetColIndex])
                        ufMap.union(source, target);
                    break;
                case DOWN:
                    targetRowIndex = row + 1;
                    targetColIndex = col;
                    target = (targetRowIndex >= grid[0].length) ? ufMapBottomIndex :
                             this.xyTo1D(targetRowIndex, targetColIndex);
                    if (targetRowIndex >= grid[0].length || grid[targetRowIndex][targetColIndex])
                        ufMap.union(source, target);
                    break;
                case RIGHT:
                    targetRowIndex = row;
                    targetColIndex = col + 1;
                    target = (targetColIndex >= grid[0].length) ? this.xyTo1D(row, col) :
                             this.xyTo1D(targetRowIndex, targetColIndex);
                    if (targetColIndex >= grid[0].length || grid[targetRowIndex][targetColIndex])
                        ufMap.union(source, target);
                    break;
                case LEFT:
                    targetRowIndex = row;
                    targetColIndex = col - 1;
                    target = (targetColIndex < 0) ? this.xyTo1D(row, col) :
                             this.xyTo1D(targetRowIndex, targetColIndex);
                    if (targetColIndex < 0 || grid[targetRowIndex][targetColIndex])
                        ufMap.union(source, target);
                    break;
            }
        }
    }
}
