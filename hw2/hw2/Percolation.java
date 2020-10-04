package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    boolean[][] grid;
    int len;
    int numberOfOpenSites;
    WeightedQuickUnionUF uf;

    public class Coordinate {

        public final int row;
        public final int col;

        public Coordinate(int a, int b) {
            row = a;
            col = b;
        }

        public Coordinate(int n) {
            row = n / len;
            col = n % len;
        }

        public int linear() {
            return row * len + col;
        }

    }

    public Percolation(int N) { // create N-by-N grid, with all sites initially blocked
        len = N;
        grid = new boolean[N][N];
        numberOfOpenSites = 0;
        uf = new WeightedQuickUnionUF(N * N + 1);
        for (int i = 0; i < N; i += 1) {
            uf.union(N * N, new Coordinate(0, i).linear());
        }
    }

    public void open(int row, int col) { // open the site (row, col) if it is not open already

        if (!isOpen(row, col)) {
            grid[row][col] = true;

            Coordinate tile = new Coordinate(row, col);
            int tileNum = tile.linear();

            if (row > 0 && isOpen(row - 1, col)) {
                Coordinate up = new Coordinate(row - 1, col);
                uf.union(up.linear(), tileNum);
            }
            if (col > 0 && isOpen(row, col - 1)) {
                Coordinate left = new Coordinate(row, col - 1);
                uf.union(left.linear(), tileNum);
            }
            if (row < len - 1 && isOpen(row + 1, col)) {
                Coordinate down = new Coordinate(row + 1, col);
                uf.union(down.linear(), tileNum);
            }
            if (col < len - 1 && isOpen(row, col + 1)) {
                Coordinate right = new Coordinate(row + 1, col);
                uf.union(right.linear(), tileNum);
            }
            numberOfOpenSites += 1;
        }
    }

    public boolean isOpen(int row, int col) { // is the site (row, col) open?
        return grid[row][col];
    }
    public boolean isFull(int row, int col) { // is the site (row, col) full?
        return (isOpen(row, col) && uf.connected(len * len, new Coordinate(row, col).linear()));
    }
    public int numberOfOpenSites() { // number of open sites
        return numberOfOpenSites;
    }
    public boolean percolates() { // does the system percolate?
        for (int i = 0; i < len; i += 1) {
            if (isFull(len - 1, i)) {
                return true;
            }
        }
        return false;
    }
    public static void main(String[] args) { // use for unit testing (not required)

    }

}
