public class QuickFind {
    private static final String path = "largeUF.txt";
    private int[] id; // access to component id (site indexed)
    private int count; // number of components

    public QuickFind(int N) { // Initialize component id array.
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

    public int find(int p) {
        return id[p];
    }

    public void union(int p, int q) { // Put p and q into the same component.
        int pID = find(p);
        int qID = find(q);
        // Nothing to do if p and q are already in the same component.
        if (pID == qID) {
            return;
        }
        // Rename p’s component to q’s name.
        for (int i = 0; i < id.length; i++) {
            if (id[i] == pID) {
                id[i] = qID;
                // Number of components--
                count--;
            }
        }
    }

    public static void main(String[] args)
    { // Solve dynamic connectivity problem on StdIn.
        int[] a = In.readInts(path);
        QuickUnion uf = new QuickUnion(a[0]); // Initialize N components.
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
