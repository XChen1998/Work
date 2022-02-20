import org.junit.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static org.junit.Assert.*;

public class MapHelper {
    public static <K, V> V get(Map61B<K, V> m, K k) {
        if (m.containsKey(k)) {
            return m.get(k);
        }
        return null;
    }

    public static <K extends Comparable<K>, V> K maxKey(Map61B<K, V> m) {
        List<K> keys = m.keys();
        K max = keys.get(0);
        for (int i = 0; i < keys.size(); i++){
            if ((max.compareTo(keys.get(i)) < 0)){
                max = keys.get(i);
            }
        }
        return max;
    }



    @Test
    public void testGet() {
        Map61B<String, Integer> m = new ArrayMap<>();
        m.put("A", 1);
        m.put("B", 2);
        m.put("C", 3);
        int expected = 3;
        int actual = MapHelper.get(m, "C");
        assertEquals(expected, actual);
    }


    @Test
    public void testMax() {
        Map61B<Integer, Integer> m = new ArrayMap<>();
        m.put(3, 1);
        m.put(2, 2);
        m.put(1, 3);
        m.put(0, 3);
        int expected = 3;
        int actual = MapHelper.maxKey(m);
        assertEquals( expected, actual);
    }
}



