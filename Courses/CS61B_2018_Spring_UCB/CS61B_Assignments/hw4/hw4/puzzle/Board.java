package hw4.puzzle;

import edu.princeton.cs.algs4.Queue;

public class Board implements WorldState {
    private final int BLANK = 0;
    private int size;
    private int[][] tiles;

    public Board(int[][] tiles) {
        this.size = tiles.length;
        this.tiles = new int[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                this.tiles[i][j] = tiles[i][j];
            }
        }
    }


    public int tileAt(int i, int j) {
        return tiles[i][j];
    }

    public int size() {
        return size;
    }

    @Override
    /*This is the original code from http://joshh.ug/neighbors.html.*/
    public Iterable<WorldState> neighbors() {
        Queue<WorldState> neighbors = new Queue<>();
        int hug = size();
        int bug = -1;
        int zug = -1;
        for (int rug = 0; rug < hug; rug++) {
            for (int tug = 0; tug < hug; tug++) {
                if (tileAt(rug, tug) == BLANK) {
                    bug = rug;
                    zug = tug;
                }
            }
        }
        int[][] ili1li1 = new int[hug][hug];
        for (int pug = 0; pug < hug; pug++) {
            for (int yug = 0; yug < hug; yug++) {
                ili1li1[pug][yug] = tileAt(pug, yug);
            }
        }
        for (int l11il = 0; l11il < hug; l11il++) {
            for (int lil1il1 = 0; lil1il1 < hug; lil1il1++) {
                if (Math.abs(-bug + l11il) + Math.abs(lil1il1 - zug) - 1 == 0) {
                    ili1li1[bug][zug] = ili1li1[l11il][lil1il1];
                    ili1li1[l11il][lil1il1] = BLANK;
                    Board neighbor = new Board(ili1li1);
                    neighbors.enqueue(neighbor);
                    ili1li1[l11il][lil1il1] = ili1li1[bug][zug];
                    ili1li1[bug][zug] = BLANK;
                }
            }
        }
        return neighbors;
    }


    public int hamming() {
        int hammingValue = 0;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (i == size - 1 && j == size - 1) {
                    break;
                }
                int curGoal = i * size + j + 1;
                if (tileAt(i, j) != curGoal) {
                    hammingValue += 1;
                }
            }
        }
        return hammingValue;
    }

    public int manhattan() {
        int manhattanValue = 0;
        for (int i = 1; i <= size * size - 1; i++) {
            int[] index = findValue(i);
            manhattanValue += manhattanHelper(index[0], index[1], i);
        }
        return manhattanValue;
    }

    private int[] findValue(int x) {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (x == tiles[i][j]) {
                    int[] returnedValue = {i, j};
                    return returnedValue;
                }
            }
        }
        return null;
    }

    private int manhattanHelper(int i, int j, int key) {
        int realRow = (key - 1) / size;
        int realCol = (key - 1) % size;
        return Math.abs(i - realRow) + Math.abs(j - realCol);

    }

    @Override
    public int estimatedDistanceToGoal() {
        return manhattan();
    }

    @Override
    public boolean equals(Object y) {
        if (y.getClass() != this.getClass()) {
            return false;
        }
        int[][] yArray = ((Board) y).tiles;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (!(this.tiles[i][j] == yArray[i][j])) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        int sum = 0;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                sum += i * size + j + 1;
            }
        }
        return sum;
    }


    /**
     * Returns the string representation of the board.
     * Uncomment this method.
     */
    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        int N = size();
        s.append(N + "\n");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                s.append(String.format("%2d ", tileAt(i, j)));
            }
            s.append("\n");
        }
        s.append("\n");
        return s.toString();
    }

}
