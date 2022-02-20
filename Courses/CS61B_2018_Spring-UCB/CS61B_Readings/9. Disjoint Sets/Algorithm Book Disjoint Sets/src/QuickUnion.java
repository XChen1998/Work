public class QuickUnion {
    private int[] id; // access to component id (site indexed)
    private int count; // number of components
    private static final String path = "largeUF.txt";

    public QuickUnion(int N) { // Initialize component id array.
        count = N;
        id = new int[N];
        for (int i = 0; i < N; i++)
            id[i] = i;
    }

    public int count() {
        return count;
    }

    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }

    private int find(int p)
    { // Find component name.
        while (p != id[p]) {
            p = id[p];
        }
        return p;
    }

    public void union(int p, int q)
    { // Give p and q the same root.
        int pRoot = find(p);
        int qRoot = find(q);
        if (pRoot == qRoot) {
            return;
        }
        id[pRoot] = qRoot;
        count--;
    }

    public static void main(String[] args)
    { // Solve dynamic connectivity problem on StdIn.
        int[] a = In.readInts(path);
        QuickFind uf = new QuickFind(a[0]); // Initialize N components.
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