/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

public class Percolation {

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        int[] x = new int[n];
        int[] y = new int[n];
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        if (row > n || col > n) {
            throw new IllegalArgumentException;
        }
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {

    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {

    }

    // returns the number of open sites
    public int numberOfOpenSites() {

    }

    // does the system percolate?
    public boolean percolates() {

    }

    public static void main(String[] args) {

    }
}
