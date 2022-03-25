package lab11.graphs;

import edu.princeton.cs.algs4.Queue;

/**
 * @author Josh Hug
 */
public class MazeBreadthFirstPaths extends MazeExplorer {
    /* Inherits public fields:
    public int[] distTo;
    public int[] edgeTo;
    public boolean[] marked;
    */
    private int s;
    private int t;
    private Queue<Integer> edges = new Queue<>();

    public MazeBreadthFirstPaths(Maze m, int sourceX, int sourceY, int targetX, int targetY) {
        super(m);
        maze = m;
        s = maze.xyTo1D(sourceX, sourceY);
        t = maze.xyTo1D(targetX, targetY);
        distTo[s] = 0;
        edgeTo[s] = s;
        edges.enqueue(s);
        marked[s] = true;
    }

    /**
     * Conducts a breadth first search of the maze starting at the source.
     */
    private void bfs() {
        announce();
        while (!edges.isEmpty()) {
            int curVertex = edges.dequeue();
            System.out.println(curVertex);
            marked[curVertex] = true;
            announce();
            if (curVertex == t) {
                return;
            }
            for (int w : maze.adj(curVertex)) {
                if (marked[w] == false) {
                    edgeTo[w] = curVertex;
                    distTo[w] = distTo[curVertex] + 1;
                    edges.enqueue(w);
                }
            }
        }

    }


    @Override
    public void solve() {
        bfs();
    }
}

