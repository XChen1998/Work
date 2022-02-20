import java.util.List;
import java.util.ArrayList;

public class ArrayMap<K, V> implements Map61B<K, V> {

    private K[] keys;
    private V[] values;
    private int size;

    public ArrayMap() {
        size = 0;
        keys = (K[]) new Object[100];
        values = (V[]) new Object[100];
    }

    private void expand() {
        K[] newKeys = (K[]) new Object[keys.length * 2];
        V[] newValues = (V[]) new Object[values.length * 2];
        System.arraycopy(keys, 0, newKeys, 0, size);
        System.arraycopy(values, 0, newValues, 0, size);
        keys = newKeys;
        values = newValues;
    }

    public int indexFinder(K key) {
        for (int i = 0; i < size; i++) {
            if (keys[i].equals(key)) {
                return i;
            }
        }
        return -1;
    }

    public void put(K key, V value) {
        if (size == keys.length) {
            expand();
        }
        if (indexFinder(key) != -1) {
            values[indexFinder(key)] = value;
        } else {
            keys[size] = key;
            values[size] = value;
            size++;
        }
    }

    public boolean containsKey(K key) {
        return !(indexFinder(key) == -1);
    }

    public V get(K key) {
        if (indexFinder(key) == -1) {
            return null;
        } else {
            return values[indexFinder(key)];
        }
    }

    public V get(Map61B m, K key){
        if (m.containsKey(key)){
            return get(key);
        }
        else {
            return null;
        }
    }

    public List<K> keys() {
        List<K> list = new ArrayList();
        for (int i = 0; i < size; i++) {
            list.add(keys[i]);
        }
        return list;
    }

    public int size() {
        return size;
    }
}
