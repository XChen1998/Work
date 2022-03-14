import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

public class DepthFirstOrder {
    private boolean[] marked;
    private Queue<Integer> pre; // vertices in preorder
    private Queue<Integer> post; // vertices in postorder
    private Stack<Integer> reversePost; // vertices in reverse postorder

    public DepthFirstOrder(Digraph G) {
        pre = new Queue<Integer>();
        post = new Queue<Integer>();
        reversePost = new Stack<Integer>();
        marked = new boolean[G.V()];
        for (int v = 0; v < G.V(); v++)
            if (!marked[v]) dfs(G, v);
    }

    private void dfs(Digraph G, int v) {
        pre.enqueue(v);
        marked[v] = true;
        for (int w : G.adj(v))
            if (!marked[w])
                dfs(G, w);
        post.enqueue(v);
        reversePost.push(v);
    }

    public Iterable<Integer> pre() {
        return pre;
    }

    public Iterable<Integer> post() {
        return post;
    }

    public Iterable<Integer> reversePost() {
        return reversePost;
    }

    public static void main(String[] args) {
        Digraph G = new Digraph(new In(args[0]));
        DepthFirstOrder order = new DepthFirstOrder(G);
        StdOut.print("Pre: ");
        for (int x : order.pre()) {
            StdOut.print(x + " ");
        }
        StdOut.println();
        StdOut.print("Post: ");
        for (int x : order.post()) {
            StdOut.print(x + " ");
        }
        StdOut.println();
        StdOut.print("Reverse Post: ");
        for (int x : order.reversePost()) {
            StdOut.print(x + " ");
        }
        StdOut.println();
    }
}