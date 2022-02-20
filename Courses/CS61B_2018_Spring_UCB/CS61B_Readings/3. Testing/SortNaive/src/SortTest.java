public class SortTest {
    /**
     * Sorts strings destructively.
     */
    public static void sort(String[] x) {
    }

    /**
     * Tests the sort method of the Sort class.
     */
    public static void testSort() {
        String[] input = {"i", "have", "an", "egg"};
        String[] expected = {"i", "egg", "have", "an"};
        sort(input);
        for (int i = 0; i < input.length; i += 1) {
            if (!(input[i] == expected[i])) { // This also works, as array[i] is a value, not address.
                System.out.println("Mismatch in position " + i + ", expected: " + expected + ", but got: " + input[i] + ".");
                break;
            }
        }
    }

    public static void main(String[] args) {
        testSort();
    }
}