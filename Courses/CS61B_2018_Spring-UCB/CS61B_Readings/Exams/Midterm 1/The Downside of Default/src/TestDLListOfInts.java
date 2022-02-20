import org.junit.Test;

import static org.junit.Assert.*;

public class TestDLListOfInts {

    @Test
    public void testAdd() {
        DLListOfInts x = new DLListOfInts();
        x.addLast(1);
        x.addLast(2);
        x.addLast(3);
        x.addLast(4);
        x.addLast(5);
        x.addLast(6);
    }

    @Test
    public void testGet() {
        DLListOfInts x = new DLListOfInts();
        x.addLast(1);
        x.addLast(2);
        x.addLast(3);
        x.addLast(4);
        x.addLast(5);
        x.addLast(6);
        int[] actual = {1, 2, 3, 4, 5, 6};
        int[] expected = new int[6];
        for (int i = 0; i < x.size(); i++) {
            expected[i] = x.get(i);
        }
        assertArrayEquals(expected, actual);
    }

    @Test
    public void testSet() {
        DLListOfInts x = new DLListOfInts();
        x.addLast(1);
        x.addLast(2);
        x.addLast(3);
        x.addLast(4);
        x.addLast(5);
        x.addLast(6);
        int[] actual = {6, 5, 4, 3, 2, 1};
        int[] expected = new int[6];
        for (int i = 0; i < x.size(); i++) {
            x.set(i, 6 - i);
            expected[i] = x.get(i);
        }
        assertArrayEquals(expected, actual);
    }

    @Test
    public void testPlusEquals() {
        DLListOfInts x = new DLListOfInts();
        x.addLast(1);
        x.addLast(2);
        x.addLast(3);
        x.addLast(4);
        x.addLast(5);
        x.addLast(6);
        DLListOfInts y = new DLListOfInts();
        y.addLast(1);
        y.addLast(3);
        y.addLast(5);
        y.addLast(6);
        y.addLast(9);
        y.addLast(11);
        x.plusEquals(y);
        int[] expected = {2, 5, 8, 10, 14, 17};
        int[] actual = new int[6];
        for (int i = 0; i < x.size(); i++) {
            actual[i] = x.get(i);
        }
        assertArrayEquals(expected, actual);
    }

    @Test
    public void testPartC() {
        DLListOfInts x = new DLListOfInts();
        x.addLast(1);
        x.addLast(2);
        x.addLast(3);

        DLListOfInts y = new DLListOfInts();
        y.addLast(1);
        y.addLast(3);
        y.addLast(5);

        DLListOfInts z = new DLListOfInts();
        z.addLast(100);
        z.addLast(200);
        z.addLast(300);
        DLListOfInts[] list = {x, y, z};
        DLListOfInts actual = PartC.sumOfLists(list);

        DLListOfInts expected = new DLListOfInts();
        expected.addLast(102);
        expected.addLast(205);
        expected.addLast(308);
        for (int i = 0; i< expected.size(); i++){
            int a = expected.get(0);
            int b = actual.get(0);
            assertEquals(a, b);
        }




    }
}