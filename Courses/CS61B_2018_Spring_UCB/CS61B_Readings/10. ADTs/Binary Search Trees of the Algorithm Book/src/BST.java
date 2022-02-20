public class BST<Key extends Comparable<Key>, Value> {
    private Node root; // root of BST

    private class Node {
        private Key key; // key
        private Value val; // associated value
        private Node left, right; // links to subtrees
        private int N; // # nodes in subtree rooted here

        public Node(Key key, Value val, int N) {
            this.key = key;
            this.val = val;
            this.N = N;
        }
    }

    public int size() {
        return size(root);
    }

    /*Avoid naked recursive.*/
    private int size(Node x) {
        if (x == null) return 0;
        else return x.N;
    }

    public Value get(Key key) {
        return get(root, key);
    }

    /*Avoid naked recursive.*/
    private Value get(Node x, Key key) {
        if (x == null) {
            return null;
        }

        int cmp = x.key.compareTo(key);
        if (cmp > 0) {
            return get(x.left, key);
        }
        if (cmp < 0) {
            return get(x.right, key);
        }
        return x.val;
    }

    public void put(Key key, Value val) {
        root = put(root, key, val);
    }

    /*Avoid naked recursive.*/
    private Node put(Node x, Key key, Value val){
        if (x == null){
            return new Node (key, val, 1);
        }
        int cmp = x.key.compareTo(key);
        if (cmp < 0) {
            x.right = put(x.right, key, val);
        }
        if (cmp > 0){
            x.left = put(x.left, key, val);
        }
        if (cmp == 0){
            x.val = val;
        }
        x.N = size(x.left) + size(x.right) + 1;
        return x;
    }
}