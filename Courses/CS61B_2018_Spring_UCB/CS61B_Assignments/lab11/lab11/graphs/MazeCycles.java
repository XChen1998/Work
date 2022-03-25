package lab11.graphs;

import edu.princeton.cs.algs4.Stack;

/**
 * @author Josh Hug
 */
public class MazeCycles extends MazeExplorer {
    /* Inherits public fields:
    public int[] distTo;
    public int[] edgeTo;
    public boolean[] marked;
    */
    private int[] circleList;

    public MazeCycles(Maze m) {
        super(m);
        circleList = new int[m.V()];
        maze = m;
        circleList[0] = 0;
    }

    @Override
    public void solve() {
        dfsCircle(0);
        announce();
    }


    // Helper methods go here
    private void dfsCircle(int v) {
        marked[v] = true;
        announce();
        System.out.println(v);
        for (int w : maze.adj(v)) {
            if (!marked[w]) {
                if (w != v){
                    int next = circleList[w];
                    int previous = w;
                    while (next != w){
                        edgeTo[next] = previous;
                        previous = next;
                        next = circleList[previous];
                    }
                    return;
                }
                circleList[w] = v;
                dfsCircle(w);
            }
        }


    }
}

