public class WeightedQuickUnion {
    private static final String path = "largeUF.txt";
    private int[] id; // parent link (site indexed)
    private int[] sz; // size of component for roots (site indexed)
    private int count; // number of components

    public WeightedQuickUnion(int N) {
        count = N;
        id = new int[N];
        for (int i = 0; i < N; i++) id[i] = i;
        sz = new int[N];
        for (int i = 0; i < N; i++) sz[i] = 1;
    }

    public int count() {
        return count;
    }

    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }

    private int find(int p) { // Follow links to find a root.
        while (p != id[p]) {
            p = id[p];
        }
        return p;
    }

    public void union(int p, int q) {
        // Find roots of p and q
        int i = find(p);
        int j = find(q);
        if (i == j) {
            return;
        }
        // Make smaller root point to larger one.
        if (sz[i] < sz[j]) {
            id[i] = j;
            sz[j] += sz[i];
        } else {
            id[j] = i;
            sz[i] += sz[j];
        }
        count--;
    }

    public static void main(String[] args)
    { // Solve dynamic connectivity problem on StdIn.
        int[] a = In.readInts(path);
        WeightedQuickUnion uf = new WeightedQuickUnion(a[0]); // Initialize N components.
        int pos = 1;
        while (pos < a.length)
        {
            int p = a[pos];
            pos++;
            int q = a[pos]; // Read pair to connect.
            pos++;
            if (uf.connected(p, q)) continue; // Ignore if connected.
            uf.union(p, q); // Combine components
            StdOut.println(p + " " + q); // and print connection.
        }
        StdOut.println(uf.count() + " components");
    }
}