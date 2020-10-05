package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private boolean[][] grid;
    private int len;
    private int numberOfOpenSites;
    private WeightedQuickUnionUF uf, uf2;

    public Percolation(int N) {
        // create N-by-N grid, with all sites initially blocked
        if (N <= 0) {
            throw new IllegalArgumentException("index " + N + " <= 0");
        }
        len = N;
        grid = new boolean[N][N];
        numberOfOpenSites = 0;
        uf = new WeightedQuickUnionUF(N * N + 1);
        uf2 = new WeightedQuickUnionUF(N * N + 2);
        for (int i = 0; i < N; i += 1) {
            uf.union(N * N, i);
            uf2.union(N * N, i);
            uf2.union(N * N - 1 - i, N * N + 1);
        }
    }

    private int to1D(int row, int col) {
        return row * len + col;
    }

    public void open(int row, int col) {
        // open the site (row, col) if it is not open already
        if (!isOpen(row, col)) {

            grid[row][col] = true;

            if (row > 0 && isOpen(row - 1, col)) {
                uf.union(to1D(row - 1, col), to1D(row, col));
                uf2.union(to1D(row - 1, col), to1D(row, col));
            }
            if (col > 0 && isOpen(row, col - 1)) {
                uf.union(to1D(row, col - 1), to1D(row, col));
                uf2.union(to1D(row, col - 1), to1D(row, col));
            }
            if (row < len - 1 && isOpen(row + 1, col)) {
                uf.union(to1D(row + 1, col), to1D(row, col));
                uf2.union(to1D(row + 1, col), to1D(row, col));
            }
            if (col < len - 1 && isOpen(row, col + 1)) {
                uf.union(to1D(row, col + 1), to1D(row, col));
                uf2.union(to1D(row, col + 1), to1D(row, col));
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
        return (isOpen(row, col) && uf.connected(len * len, to1D(row, col)));
    }
    public int numberOfOpenSites() {
        // number of open sites
        return numberOfOpenSites;
    }
    public boolean percolates() {
        // does the system percolate?
        if (len == 1) {
            return isOpen(0, 0);
        }
        return uf2.connected(len * len, len * len + 1);
    }
    public static void main(String[] args) {
        // use for unit testing (not required)

    }

}
