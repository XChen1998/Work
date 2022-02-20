import org.junit.Test;

import java.util.*;

public class Flatten {
    public static int[] flatten(int[][] x) {
        int totalLength = 0;
        for (int i = 0; i < x.length; i++) {
            totalLength += x[i].length;
        }
        int[] a = new int[totalLength];
        int aIndex = 0;
        for (int i = 0; i < x.length; i++) {
            for (int j = 0; j < x[i].length; j++) {
                a[aIndex] = x[i][j];
                aIndex++;
            }
        }
        return a;
    }

    @Test
    public void testFlatten() {
        int[][] x = {{1, 2, 3}, {}, {7, 8}};
        int[] expected = {1, 2, 3, 7, 8};
        int[] actual = flatten(x);
        System.out.println(Arrays.toString(actual));
        org.junit.Assert.assertArrayEquals(actual, expected);
    }

}
