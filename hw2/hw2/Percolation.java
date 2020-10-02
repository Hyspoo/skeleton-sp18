package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    boolean[][] grid;
    int numberOfOpenSites;

    public Percolation(int N) { // create N-by-N grid, with all sites initially blocked
        grid = new boolean[N][N];
        numberOfOpenSites = 0;
    }

    public void open(int row, int col) { // open the site (row, col) if it is not open already
        if (!grid[row][col]) {
            numberOfOpenSites += 1;
            grid[row][col] = true;
        }
    }

    public boolean isOpen(int row, int col) { // is the site (row, col) open?
        return grid[row][col];
    }
    public boolean isFull(int row, int col) { // is the site (row, col) full?
        return false;
    }
    public int numberOfOpenSites() { // number of open sites
        return numberOfOpenSites;
    }
    public boolean percolates() { // does the system percolate?
        return false;
    }
    public static void main(String[] args) { // use for unit testing (not required)
    }

}
