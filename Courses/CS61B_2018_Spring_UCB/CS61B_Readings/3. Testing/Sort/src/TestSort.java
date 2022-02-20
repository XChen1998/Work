import org.junit.Test;


public class TestSort {
    @Test
    public void testsort() {
        String[] input = {"i", "have", "an", "egg"};
        String[] expected = {"an", "egg", "have", "i"};
        Sort.sort(input);

        org.junit.Assert.assertArrayEquals(expected, input);
    }


    @Test
    public void testFindSmallest() {
        String[] input = {"i", "have", "an", "egg"};
        String expected = "an";

        String actual = input[Sort.findSmallest(input, 0)];
        org.junit.Assert.assertEquals(expected, actual);

        String[] input2 = {"there", "are", "many", "pigs"};
        String expected2 = "are";

        String actual2 = input2[Sort.findSmallest(input2, 0)];
        org.junit.Assert.assertEquals(expected2, actual2);
    }

    @Test
    public void testswap() {
        String[] input = {"i", "have", "an", "egg"};
        String[] expected = {"an", "have", "i", "egg"};
        int a = 0;
        int b = 2;
        Sort.swap(input, a, b);
        org.junit.Assert.assertArrayEquals(expected, input);

    }
}