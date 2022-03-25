package lab11.graphs;

/**
 *  @author Josh Hug
 */
public class MazeCycles extends MazeExplorer {
    /* Inherits public fields:
    public int[] distTo;
    public int[] edgeTo;
    public boolean[] marked;
    */
    boolean hasCircle;

    public MazeCycles(Maze m) {
        super(m);
        hasCircle = false;
    }

    @Override
    public void solve() {
        
    }


    // Helper methods go here
}

