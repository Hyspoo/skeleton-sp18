package hw2;

import edu.princeton.cs.introcs.StdRandom;
import edu.princeton.cs.introcs.StdStats;

public class PercolationStats {

    double[] result;
    int times;

    public PercolationStats(int N, int T, PercolationFactory pf) { // perform T independent experiments on an N-by-N grid

        times = T;
        result = new double[T];

        for (int i = 0; i < T; i += 1) {
            Percolation p = pf.make(N);
            // randomly open tiles until percolate
            while (!p.percolates()) {
                Percolation.Coordinate tile = p.new Coordinate(StdRandom.uniform(N * N));
                if (!p.isOpen(tile.row, tile.col)) {
                    p.open(tile.row, tile.col);
                }
            }
            result[i] = (double)(p.numberOfOpenSites()) / (N * N);
        }

    }

    public double mean() { // sample mean of percolation threshold
        return StdStats.mean(result);
    }

    public double stddev() { // sample standard deviation of percolation threshold
        return StdStats.stddev(result);
    }

    public double confidenceLow() { // low endpoint of 95% confidence interval
        return mean() - (1.96 * stddev() / Math.sqrt(times));
    }

    public double confidenceHigh() { // high endpoint of 95% confidence interval
        return mean() + (1.96 * stddev() / Math.sqrt(times));
    }

}
