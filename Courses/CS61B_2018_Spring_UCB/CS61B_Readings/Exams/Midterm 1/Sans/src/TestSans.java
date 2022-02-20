import org.junit.Test;

import static org.junit.Assert.*;

public class TestSans {
    @Test
    public void testSans() { // TEST THE ARRAY VERSION OF SANS
        int[] x = {3, 2, 3, 4, 3};
        int y = 3;
        int[] expected = {2, 4};
        int[] actual = IntList.sans(x, y);
        assertArrayEquals(expected, actual);


    }

    @Test // TEST THE NON-DESTRUCTIVE INTLIST VERSION OF SANS
    public void testIlsans() {
        IntList x = IntList.of(2, 3, 4, 2, 3, 4, 2, 3, 4);
        int y = 3;
        IntList expected = IntList.of(2, 4, 2, 4, 2, 4);
        IntList actual = IntList.ilsans(x, y);
        assertEquals(expected, actual);
        assertNotEquals(x, actual);
    }

    @Test // TEST THE DESTRUCTIVE INTLIST VERSION OF SANS
    public void testDilsans() {
        IntList x = IntList.of(2, 3, 4, 2, 3, 4, 2, 3, 4);
        int y = 3;
        IntList expected = IntList.of(2, 4, 2, 4, 2, 4);
        IntList actual = IntList.dilsans(x, y);
        assertEquals(expected, actual);
        assertEquals(x, actual);
    }
}