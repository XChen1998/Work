import java.util.List;
public interface Map61B<K, V> {
    public void put(K key, V value);
    public boolean containsKey(K key);
    public V get(K key);
    public List keys();
    public int size();

}
