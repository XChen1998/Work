import org.junit.Test;
import java.util.HashSet;
import java.util.Set;
import static org.junit.Assert.*;

public class twoSum {
    public boolean twoSumFunc(int[] A, int k) {
        Set<Integer> hashSet = new HashSet<>();
        for (int i = 0; i < A.length; i++) {
            if (hashSet.contains(k - A[i])) {
                return true;
            } else hashSet.add(A[i]);
        }
        return false;
    }

    @Test
    public void testtwoSumFunc() {
        int[] A = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        boolean expected1 = true;
        boolean expected2 = false;
        boolean actual1 = twoSumFunc(A, 18);
        boolean actual2 = twoSumFunc(A, 20);
        assertEquals(expected1, actual1);
        assertEquals(expected2, actual2);
    }
}

