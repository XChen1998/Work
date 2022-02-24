package lab9;

import java.util.*;

import edu.princeton.cs.algs4.*;
/**
 * A hash table-backed Map implementation. Provides amortized constant time
 * access to elements via get(), remove(), and put() in the best case.
 *
 * @author Your name here
 */
public class MyHashMap<K, V> implements Map61B<K, V> {

    private static final int DEFAULT_SIZE = 16;
    private static final double MAX_LF = 0.75;

    private ArrayMap<K, V>[] buckets;
    private int size;

    private int loadFactor() {
        return size / buckets.length;
    }

    public MyHashMap() {
        buckets = new ArrayMap[DEFAULT_SIZE];
        this.clear();
    }

    /* Removes all of the mappings from this map. */
    @Override
    public void clear() {
        this.size = 0;
        for (int i = 0; i < this.buckets.length; i += 1) {
            this.buckets[i] = new ArrayMap<>();
        }
    }

    /**
     * Computes the hash function of the given key. Consists of
     * computing the hashcode, followed by modding by the number of buckets.
     * To handle negative numbers properly, uses floorMod instead of %.
     */
    private int hash(K key) {
        if (key == null) {
            return 0;
        }
        int numBuckets = buckets.length;
        return Math.floorMod(key.hashCode(), numBuckets);
    }

    /* Returns the value to which the specified key is mapped, or null if this
     * map contains no mapping for the key.
     */
    @Override
    public V get(K key) {
        if (key == null) {
            throw new IllegalArgumentException();
        }
        return buckets[hash(key)].get(key);
    }

    /* Associates the specified value with the specified key in this map. */
    @Override
    public void put(K key, V value) {
        if (key == null) {
            throw new IllegalArgumentException();
        }
        if (!buckets[hash(key)].containsKey(key)) {
            size++;
        }
        buckets[hash(key)].put(key, value);
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
        Set returned = new HashSet();
        for (int i = 0; i < buckets.length; i++) {
            for (K key : buckets[i]) {
                returned.add(key);
            }
        }
        return returned;
    }

    /* Removes the mapping for the specified key from this map if exists.
     * Not required for this lab. If you don't implement this, throw an
     * UnsupportedOperationException. */
    @Override
    public V remove(K key) {
        if (key == null){
            throw new IllegalArgumentException();
        }
        if (get(key) == null) {
            return null;
        } else {
            size--;
            return buckets[hash(key)].remove(key);
        }
    }

    /* Removes the entry for the specified key only if it is currently mapped to
     * the specified value. Not required for this lab. If you don't implement this,
     * throw an UnsupportedOperationException.*/
    @Override
    public V remove(K key, V value) {
        if (get(key) == null){
            throw new IllegalArgumentException();
        }
        if (get(key)!= value){
            return null;
        } else{
            size--;
            return buckets[hash(key)].remove(key);
        }
    }

    @Override
    public Iterator<K> iterator() {
        return new MyHashMapIterator();

    }

    private class MyHashMapIterator implements Iterator<K> {
        private int pos;
        private K[] keys = (K[]) new Objects[size];
        private Set<K> set;

        public MyHashMapIterator(){
            pos = 0;
            set = keySet();
            keys = (K[]) set.toArray();
        }

        @Override
        public boolean hasNext() {
            return pos < size;
        }

        @Override
        public K next() {
            K returned = keys[pos];
            pos++;
            return returned;
        }
    }

}
