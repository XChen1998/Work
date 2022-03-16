package hw4.puzzle;

import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Stack;

import java.util.Comparator;

public class Solver {

    private MinPQ<SearchNodes> pq;
    private Stack<WorldState> path;

    private class SearchNodes implements Comparable<SearchNodes> {
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
        public int compareTo(SearchNodes o) {
            int thisValue = this.moves() + this.curWorldState().estimatedDistanceToGoal();
            int oValue = o.moves() + o.curWorldState().estimatedDistanceToGoal();
            return thisValue - oValue;
        }
    }

    public Solver(WorldState initial) {
        pq = new MinPQ<>();
        SearchNodes initialSN = new SearchNodes(0, null, initial);
        pq.insert(initialSN);
        path = new Stack<>();
        while (!pq.isEmpty()) {
            if (pq.min().curWorldState().isGoal()){
                createPath(pq.min());
                break;
            }

            relax(pq.delMin());
        }

    }

    private void relax(SearchNodes sn) {
        if (sn.curWorldState().isGoal()) {
            return;
        } else {
            for (WorldState ws : sn.curWorldState().neighbors()) {
                SearchNodes cur = new SearchNodes(sn.moves() + 1, sn, ws);
                if (sn.previous == null || !ws.equals(sn.previous.curWorldState())) {
                    pq.insert(cur);
                }
            }
        }

    }
    private void createPath(SearchNodes sn){
        while (sn != null){
            path.push(sn.curWorldState());
            sn = sn.previousNode();
        }
    }

    public int moves() {
        return path.size() - 1;
    }

    public Iterable<WorldState> solution() {
        return path;
    }
}