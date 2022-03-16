package hw4.puzzle;

import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Stack;

import java.util.Comparator;

public class Solver {

    private MinPQ<SearchNodes> pq;
    private Stack<SearchNodes> path;

    private class SearchNodes implements Comparator<SearchNodes> {
        private int numOfMoves;
        private SearchNodes previous;
        private WorldState ws;

        public SearchNodes(int numOfMoves, SearchNodes previous, WorldState ws) {
            this.numOfMoves = numOfMoves;
            this.previous = previous;
            this.ws = ws;
        }

        public int moves() {
            return numOfMoves;
        }

        public SearchNodes previousNode() {
            return previous;
        }

        public WorldState curWorldState() {
            return ws;
        }

        @Override
        public int compare(SearchNodes o1, SearchNodes o2) {
            int o1Value = o1.moves() + o1.curWorldState().estimatedDistanceToGoal();
            int o2Value = o2.moves() + o2.curWorldState().estimatedDistanceToGoal();
            return o1Value - o2Value;
        }
    }

    public Solver(WorldState initial) {
        pq = new MinPQ<>();
        SearchNodes initialSN = new SearchNodes(0, null, initial);
        pq.insert(initialSN);
        while (!pq.isEmpty()) {
            relax(pq.delMin());
        }


    }

    private void relax(SearchNodes sn) {
        if (sn.curWorldState().isGoal()) {
            return;
        } else {
            for (WorldState ws : sn.curWorldState().neighbors()) {
                SearchNodes cur = new SearchNodes(sn.moves(), sn, ws);
                pq.insert(cur);
            }
        }

    }

    public int moves() {
        return path.size();
    }

    public Iterable<SearchNodes> solution() {


        return path;
    }
}