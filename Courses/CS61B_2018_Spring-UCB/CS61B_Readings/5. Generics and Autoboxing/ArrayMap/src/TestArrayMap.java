import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class TestArrayMap {
    @Test
    public void testPut() {
        ArrayMap<Integer, Integer> test = new ArrayMap<>();
        for (int i = 1; i < 205; i++) {
            test.put(i, i);
        }
        boolean flag = test.containsKey((12));
        assertEquals(true, flag);
        int size = test.size();
        assertEquals(204, size);
        int value = test.get(20);
        assertEquals(20, value);
        List<Integer> testString = test.keys();
        List<Integer> expected = new ArrayList();
        for (int i = 1; i < 205; i++) {
            expected.add(i);
        }
        assertEquals(expected, testString);
    }

    @Test
    public void testString() {
        ArrayMap<String, Integer> test = new ArrayMap<>();
        for (int i = 0; i < 205; i++) {
            test.put("NO." + i, i);
        }
        boolean flag = test.containsKey(("NO.5"));
        assertEquals(true, flag);
        int size = test.size();
        assertEquals(205, size);
        int value = test.get("NO.25");
        assertEquals(25, value);
        List<String> testString = test.keys();
        List<String> expected = new ArrayList();
        for (int i = 0; i < 205; i++) {
            expected.add("NO." + i);
        }
        assertEquals(expected, testString);


    }

    @Test
    public void test() {
        ArrayMap<Integer, Integer> am = new ArrayMap<>();
        am.put(2, 5);
        int expected = 5;
        assertEquals(expected, (int) am.get(2));
        assertEquals((Integer) expected, am.get(2));
        assertEquals(expected, (long) am.get(2));

    }
}
