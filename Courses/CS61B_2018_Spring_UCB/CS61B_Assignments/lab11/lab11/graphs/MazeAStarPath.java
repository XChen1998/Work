package lab11.graphs;

import edu.princeton.cs.algs4.MinPQ;

/**
 * @author Josh Hug
 */
public class MazeAStarPath extends MazeExplorer {
    private int s;
    private int t;
    private boolean targetFound = false;
    private Maze maze;
    private MinPQ<MazeNode> pq = new MinPQ<>();

    private class MazeNode implements Comparable<MazeNode> {
        private int nodeNum;

        MazeNode(int x) {
            nodeNum = x;
        }

        public int node() {
            return nodeNum;
        }


        @Override
        public int compareTo(MazeNode o) {
            return h(this.nodeNum) - h(o.nodeNum);
        }
    }


    public MazeAStarPath(Maze m, int sourceX, int sourceY, int targetX, int targetY) {
        super(m);
        maze = m;
        s = maze.xyTo1D(sourceX, sourceY);
        t = maze.xyTo1D(targetX, targetY);
        distTo[s] = 0;
        edgeTo[s] = s;
    }

    /**
     * Estimate of the distance from v to the target.
     */
    private int h(int v) {
        return Math.abs(maze.toX(v) - maze.toX(t))
                + Math.abs(maze.toY(v) - maze.toY(t));
    }

    /**
     * Performs an A star search from vertex s.
     */
    private void astar(int x) {
        announce();
        pq.insert(new MazeNode(x));
        while (!pq.isEmpty() || pq.min().node() == t) {
            MazeNode curNode = pq.delMin();
            int nodeNum = curNode.node();
            marked[nodeNum] = true;
            announce();
            if (nodeNum == t) {
                return;
            }
            for (int w : maze.adj(nodeNum)) {
                if (!marked[w]) {
                    edgeTo[w] = nodeNum;
                    distTo[w] = distTo[nodeNum] + 1;
                    pq.insert(new MazeNode(w));
                }
            }
        }
    }

    @Override
    public void solve() {
        astar(s);
    }

}

