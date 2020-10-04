package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private boolean[][] grid;
    private int len;
    private int numberOfOpenSites;
    private WeightedQuickUnionUF uf;

    public Percolation(int N) {
        // create N-by-N grid, with all sites initially blocked
        len = N;
        grid = new boolean[N][N];
        numberOfOpenSites = 0;
        uf = new WeightedQuickUnionUF(N * N + 1);
        for (int i = 0; i < N; i += 1) {
            uf.union(N * N, i);
        }
    }

    int To1D(int row, int col) {
        return row * len + col;
    }

    public void open(int row, int col) {
        // open the site (row, col) if it is not open already
        if (!isOpen(row, col)) {

            grid[row][col] = true;

            if (row > 0 && isOpen(row - 1, col)) {
                uf.union(To1D(row - 1, col), To1D(row, col));
            }
            if (col > 0 && isOpen(row, col - 1)) {
                uf.union(To1D(row, col - 1), To1D(row, col));
            }
            if (row < len - 1 && isOpen(row + 1, col)) {
                uf.union(To1D(row + 1, col), To1D(row, col));
            }
            if (col < len - 1 && isOpen(row, col + 1)) {
                uf.union(To1D(row, col + 1), To1D(row, col));
            }

            numberOfOpenSites += 1;

        }
    }

    public boolean isOpen(int row, int col) {
        // is the site (row, col) open?
        return grid[row][col];
    }
    public boolean isFull(int row, int col) {
        // is the site (row, col) full?
        return (isOpen(row, col) && uf.connected(len * len, To1D(row, col)));
    }
    public int numberOfOpenSites() {
        // number of open sites
        return numberOfOpenSites;
    }
    public boolean percolates() {
        // does the system percolate?
        for (int i = 0; i < len; i += 1) {
            if (isFull(len - 1, i)) {
                return true;
            }
        }
        return false;
    }
    public static void main(String[] args) {
        // use for unit testing (not required)

    }

}
