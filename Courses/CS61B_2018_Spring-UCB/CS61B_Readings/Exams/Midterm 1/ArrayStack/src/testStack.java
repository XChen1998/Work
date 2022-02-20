import org.junit.Test;

import static org.junit.Assert.*;

public class testStack {
    @Test
    public void testPushPop() {
        ArrayStack<Integer> actual = new ArrayStack<>();
        for (int i = 0; i < 5; i++) {
            actual.push(i);
        }
        int[] expected = {4, 3, 2, 1, 0};
        for (int i = 0; i < actual.size(); i++) {
            assertEquals(expected[i], (int) actual.pop());
        }
    }

    @Test
    public void testPurge() {
        ArrayStack<Integer> actual = new ArrayStack<>();
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 3; j++) {
                actual.push(j);
            }
        }
        actual.purge(2);
        ArrayStack<Integer> expected = new ArrayStack<>();
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 2; j++) {
                expected.push(j);
            }
        }
        for (int i = 0 ; i< expected.size(); i++) {
            assertEquals(expected.pop(), actual.pop());
        }
    }

    @Test
    public void testPurgeElegant() {
        ArrayStack<Integer> actual = new ArrayStack<>();
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 3; j++) {
                actual.push(j);
            }
        }
        actual.purgeElegant(2);
        ArrayStack<Integer> expected = new ArrayStack<>();
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 2; j++) {
                expected.push(j);
            }
        }
        for (int i = 0 ; i< expected.size(); i++) {
            assertEquals(expected.pop(), actual.pop());
        }
    }
}
