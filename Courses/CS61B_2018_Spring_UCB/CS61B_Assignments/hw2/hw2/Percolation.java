package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

import java.util.ArrayList;

public class Percolation {
    private WeightedQuickUnionUF set;
    private WeightedQuickUnionUF setIsFull;
    private boolean[][] grid;
    private int source;
    private int sink;
    private int numberOfOpenSites;

    public Percolation(int N) {
        if (N <= 0) {
            throw new IllegalArgumentException("Cannot set grid with side less than 1.");
        }
        grid = new boolean[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                grid[i][j] = false; // 0 is blocked, 1 is open, 2 is full
            }
        }
        set = new WeightedQuickUnionUF(N * N + 2);
        setIsFull = new WeightedQuickUnionUF(N * N + 1);
        source = N * N;
        sink = N * N + 1;
        numberOfOpenSites = 0;
        for (int i = 0; i < N; i++) {
            setIsFull.union(source, index2number(0, i));
            set.union(source, index2number(0, i));
            set.union(sink, index2number(N - 1, i));
        }
    }                // create N-by-N grid, with all sites initially blocked

    private boolean checkInBound(int row, int col) {
        return !(row < 0 || col < 0 || row >= grid.length || col >= grid.length);
    }

    private void checkValidity(int row, int col) {
        if (!checkInBound(row, col)) {
            throw new IndexOutOfBoundsException("Index out of bound.");
        }
    }

    private int index2number(int row, int col) {
        return row * grid.length + col;
    }

    private int number2row(int number) {
        return number / grid.length;
    }

    private int number2col(int number) {
        return number % grid.length;
    }

    private void unionNeighbours(int row, int col) {

        ArrayList<Integer> blocks = new ArrayList<>();
        boolean neighbourIsFull = false;
        if (checkInBound(row - 1, col)) {
            if (isFull(row - 1, col)) {
                neighbourIsFull = true;
            }
            if (isOpen(row - 1, col)) {
                blocks.add(index2number(row - 1, col));
            }
        }

        if (checkInBound(row + 1, col)) {
            if (isFull(row + 1, col)) {
                neighbourIsFull = true;
            }
            if (isOpen(row + 1, col)) {
                blocks.add(index2number(row + 1, col));
            }
        }

        if (checkInBound(row, col - 1)) {
            if (isFull(row, col - 1)) {
                neighbourIsFull = true;
            }
            if (isOpen(row, col - 1)) {
                blocks.add(index2number(row, col - 1));
            }
        }

        if (checkInBound(row, col + 1)) {
            if (isFull(row, col + 1)) {
                neighbourIsFull = true;
            }
            if (isOpen(row, col + 1)) {
                blocks.add(index2number(row, col + 1));
            }
        }

        if (neighbourIsFull) {
            setIsFull.union(index2number(row, col), source);
            set.union(index2number(row, col), source);
            for (int n : blocks) {
                setIsFull.union(n, source);
                set.union(n, source);
            }

        } else {
            for (int n : blocks) {
                setIsFull.union(n, index2number(row, col));
                set.union(n, index2number(row, col));
            }
        }
    }

    // open the site (row, col) if it is not open already
    public void open(int row, int col) {
        if (isOpen(row, col)) {
            return;
        }
        grid[row][col] = true;
        checkValidity(row, col);
        numberOfOpenSites++;
        unionNeighbours(row, col);
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        checkValidity(row, col);
        return grid[row][col];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        checkValidity(row, col);
        if (row == 0 || row == grid.length - 1) {
            return isOpen(row, col) && setIsFull.connected(source, index2number(row, col));
        } else {
            return setIsFull.connected(source, index2number(row, col));
        }
    }

    // number of open sites
    public int numberOfOpenSites() {
        return numberOfOpenSites;
    }

    // does the system percolate?
    public boolean percolates() {
        if (grid.length == 1) {
            return numberOfOpenSites == 1;
        }
        return set.connected(source, sink);
    }

    public static void main(String[] args) {
        PercolationStats test = new PercolationStats(100, 2, new PercolationFactory());
    }
}
