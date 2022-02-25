package lab9;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * Implementation of interface Map61B with BST as core data structure.
 *
 * @author Your name here
 */
public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V> {

    private class Node {
        /* (K, V) pair stored in this Node. */
        private K key;
        private V value;

        /* Children of this Node. */
        private Node left;
        private Node right;

        private Node(K k, V v) {
            key = k;
            value = v;
        }
    }

    private Node root;  /* Root node of the tree. */
    private int size; /* The number of key-value pairs in the tree */

    /* Creates an empty BSTMap. */
    public BSTMap() {
        this.clear();
    }

    /* Removes all of the mappings from this map. */
    @Override
    public void clear() {
        root = null;
        size = 0;
    }

    /**
     * Returns the value mapped to by KEY in the subtree rooted in P.
     * or null if this map contains no mapping for the key.
     */
    private V getHelper(K key, Node p) {
        if (p == null) {
            return null;
        }
        int cmp = key.compareTo(p.key);
        if (cmp < 0) {
            return getHelper(key, p.left);
        } else if (cmp > 0) {
            return getHelper(key, p.right);
        } else {
            return p.value;
        }
    }

    /**
     * Returns the value to which the specified key is mapped, or null if this
     * map contains no mapping for the key.
     */
    @Override
    public V get(K key) {
        if (key == null) {
            throw new IllegalArgumentException();
        }
        return getHelper(key, root);
    }

    /**
     * Returns a BSTMap rooted in p with (KEY, VALUE) added as a key-value mapping.
     * Or if p is null, it returns a one node BSTMap containing (KEY, VALUE).
     */
    private Node putHelper(K key, V value, Node p) {
        if (root == null) {
            root = new Node(key, value);
            return root;
        }
        if (p == null) {
            p = new Node(key, value);
        } else {
            int cmp = key.compareTo(p.key);
            if (cmp < 0) {
                p.left = putHelper(key, value, p.left);
            } else if (cmp > 0) {
                p.right = putHelper(key, value, p.right);
            } else {
                size--;
                p.value = value;
            }
        }
        return p;


    }

    /**
     * Inserts the key KEY
     * If it is already present, updates value to be VALUE.
     */
    @Override
    public void put(K key, V value) {
        if (key == null) {
            throw new IllegalArgumentException();
        }
        if (value == null) {
            return;
        }
        putHelper(key, value, root);
        size++;
    }

    /* Returns the number of key-value mappings in this map. */
    @Override
    public int size() {
        return size;
    }

    //////////////// EVERYTHING BELOW THIS LINE IS OPTIONAL ////////////////

    /* Returns a Set view of the keys contained in this map. */
    @Override
    public Set<K> keySet() {
        Set<K> set = new HashSet<>();
        traverse(set, root);
        return set;
    }

    private void traverse(Set<K> set, Node n) {
        if (n == null) {
            return;
        }
        traverse(set, n.left);
        set.add(n.key);
        traverse(set, n.right);
    }

    /**
     * Removes KEY from the tree if present
     * returns VALUE removed,
     * null on failed removal.
     */
    @Override
    public V remove(K key) {
        if (key == null) {
            throw new IllegalArgumentException();
        }
        V value = get(key);
        if (get(key) != null) {
            root = remove(key, root);
        }
        size--;
        return value;
    }

    private Node remove(K key, Node n) {
        if (n == null) {
            return null;
        }
        int cmp = key.compareTo(n.key);
        if (cmp < 0) {
            n.left = remove(key, n.left);
        } else if (cmp > 0) {
            n.right = remove(key, n.right);
        } else {
            Node t = n;
            n = min(t.right);
            if (n != null) {
                n.right = deleteMin(t.right);
                n.left = t.left;
            }
            if(n == null){
                return t.left;
            }
        }
        return n;
    }

    private Node min(Node n) {
        if (n == null) {
            return null;
        }
        if (n.left != null) {
            return min(n.left);
        } else {
            return n;
        }
    }

    private Node deleteMin(Node x) {
        if (x.left == null) return x.right;
        x.left = deleteMin(x.left);
        return x;
    }

    /**
     * Removes the key-value entry for the specified key only if it is
     * currently mapped to the specified value.  Returns the VALUE removed,
     * null on failed removal.
     **/
    @Override
    public V remove(K key, V value) {
        if (get(key) == value){
            return remove(key);
        }
        return null;
    }

    @Override
    public Iterator<K> iterator() {
        Set<K> set = keySet();
        return set.iterator();
    }

    public static void main(String[] args) {
        BSTMap<String, Integer> bstmap = new BSTMap<>();
        bstmap.put("S", 5);
        bstmap.put("E", 10);
        bstmap.put("A", 22);
        bstmap.put("R", 90);
        bstmap.put("C", 91);
        bstmap.put("H", 92);
        bstmap.put("M", 93);
        bstmap.remove("R");
    }
}
