import edu.princeton.cs.algs4.Edge;
import edu.princeton.cs.algs4.IndexMinPQ;

import java.util.Stack;

public class PrimMST {
    private Edge[] edgeTo; // shortest edge from tree vertex
    private double[] distTo; // distTo[w] = edgeTo[w].weight()
    private boolean[] marked; // true if v on tree
    private IndexMinPQ<Double> pq; // eligible crossing edges

    public PrimMST(EdgeWeightedGraph G) {
        edgeTo = new Edge[G.V()];
        distTo = new double[G.V()];
        marked = new boolean[G.V()];
        for (int v = 0; v < G.V(); v++)
            distTo[v] = Double.POSITIVE_INFINITY;
        pq = new IndexMinPQ<Double>(G.V());
        distTo[0] = 0.0;
        pq.insert(0, 0.0); // Initialize pq with 0, weight 0.
        while (!pq.isEmpty())
            visit(G, pq.delMin()); // Add closest vertex to tree.
    }

    private void visit(EdgeWeightedGraph G, int v) { // Add v to tree; update data structures.
        marked[v] = true;
        for (Edge e : G.adj(v)) {
            int w = e.other(v);
            if (marked[w]) continue; // v-w is ineligible.
            if (e.weight() < distTo[w]) { // Edge e is new best connection from tree to w.
                edgeTo[w] = e;
                distTo[w] = e.weight();
                if (pq.contains(w)) pq.change(w, distTo[w]);
                else pq.insert(w, distTo[w]);
            }
        }
    }

    public Iterable<Edge> edges() {
        // XC: Please note that the Java Stack has different Iterable behaviour compared with the algs4 stack.
        // --> Java: Ordered, algs4: Reverse ordered.
        Stack<Edge> returned = new Stack<>();
        for (Edge e : edgeTo) {
            if (e != null) {
                returned.push(e);
            }
        }
        return returned;
    }

    public double weight() {
        double totalWeight = 0;
        for (double w : distTo) {
            totalWeight += w;
        }
        return totalWeight;
    }


    public static void main(String[] args) {
        In in = new In(args[0]);
        EdgeWeightedGraph G = new EdgeWeightedGraph(in);
        PrimMST mst = new PrimMST(G);
        for (Edge e : mst.edges()) {
            StdOut.println(e);
        }
        StdOut.printf("%.5f\n", mst.weight());
    }
}