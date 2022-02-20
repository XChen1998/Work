public interface DisjointSets {
    /** Connets two items P and Q. **/
    void connect(int p, int q);

    /** Checks if two items are connected. **/
    boolean isConnected(int p, int q);
}
