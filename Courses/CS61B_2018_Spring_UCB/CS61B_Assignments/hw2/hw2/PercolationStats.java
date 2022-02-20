package hw2;

import edu.princeton.cs.introcs.StdRandom;
import edu.princeton.cs.introcs.StdStats;

public class PercolationStats {
    private int sideLength;
    private int times;
    private double[] threshold;
    private PercolationFactory p;
    private double meanStat;
    private double stddevStat;
    private double confidenceLowStat;
    private double confidenceHighStat;

    public PercolationStats(int N, int T, PercolationFactory pf) {
        if (N <= 0 || T <= 0) {
            throw new IllegalArgumentException("Illegal Argument!");
        }
        sideLength = N;
        times = T;
        threshold = new double[T];
        p = pf;
        makeSimulations();
    }  // perform T independent experiments on an N-by-N grid

    private void makeSimulations() {
        for (int i = 0; i < times; i++) {
            threshold[i] = makeSingleSimulation(p.make(sideLength));
        }
        calculateStatistics();
    }

    private double makeSingleSimulation(Percolation pInstance) {
        while (!pInstance.percolates()) {
            pInstance.open(StdRandom.uniform(sideLength), StdRandom.uniform(sideLength));
        }
        return (double) pInstance.numberOfOpenSites() / (double) sideLength / (double) sideLength;
    }

    private void calculateStatistics() {
        meanStat = StdStats.mean(threshold);
        stddevStat = StdStats.stddev(threshold);
        confidenceLowStat = meanStat - 1.96 * stddevStat / Math.sqrt(times);
        confidenceHighStat = meanStat + 1.96 * stddevStat / Math.sqrt(times);
    }

    // sample mean of percolation threshold
    public double mean() {
        return meanStat;
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return stddevStat;
    }

    // low endpoint of 95% confidence interval
    public double confidenceLow() {
        return confidenceLowStat;
    }

    // high endpoint of 95% confidence interval
    public double confidenceHigh() {
        return confidenceHighStat;
    }
}
