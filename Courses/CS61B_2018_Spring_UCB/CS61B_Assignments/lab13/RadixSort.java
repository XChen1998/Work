import org.junit.Test;

/**
 * Class for doing Radix sort
 *
 * @author Akhil Batra, Alexander Hwang
 */
public class RadixSort {
    /**
     * Does LSD radix sort on the passed in array with the following restrictions:
     * The array can only have ASCII Strings (sequence of 1 byte characters)
     * The sorting is stable and non-destructive
     * The Strings can be variable length (all Strings are not constrained to 1 length)
     *
     * @param asciis String[] that needs to be sorted
     * @return String[] the sorted array
     */
    public static String[] sort(String[] asciis) {
        String[] sorted = new String[asciis.length];
        for (int i = 0; i < asciis.length; i++) {
            sorted[i] = asciis[i];
        }
        int maxLength = Integer.MIN_VALUE;
        for (String s : sorted) {
            maxLength = maxLength > s.length() ? maxLength : s.length();
        }
        for (int i = maxLength - 1; i >= 0; i--) {
            sortHelperLSD(sorted, i);
        }

        return sorted;
    }

    /**
     * LSD helper method that performs a destructive counting sort the array of
     * Strings based off characters at a specific index.
     *
     * @param asciis Input array of Strings
     * @param index  The position to sort the Strings on.
     */
    private static void sortHelperLSD(String[] asciis, int index) {
        /*XChen: In this example, we use counting sort,
        but we can also use other ways to sort them. (Merge sort etc.)*/
        int[] intArray = new int[asciis.length];
        for (int i = 0; i < asciis.length; i++) {
            intArray[i] = char2int(asciis[i], index);
        }


        int[] counts = new int[256];
        for (int i : intArray) {
            counts[i]++;
        }
        int[] pos = new int[counts.length];
        int cumulativePos = 0;
        for (int i = 0; i < counts.length; i++) {
            pos[i] = cumulativePos;
            cumulativePos += counts[i];
        }
        String[] sorted = new String[asciis.length];
        for (int i = 0; i < asciis.length; i++) {
            sorted[pos[intArray[i]]] = asciis[i];
            pos[intArray[i]]++;
        }

        for (int i = 0; i < asciis.length; i++) {
            asciis[i] = sorted[i];
        }
    }

    private static int char2int(String s, int index) {
        if (s.length() > index) {
            return (int) s.charAt(index);
        } else {
            return 0;
        }
    }

    /**
     * MSD radix sort helper function that recursively calls itself to achieve the sorted array.
     * Destructive method that changes the passed in array, asciis.
     *
     * @param asciis String[] to be sorted
     * @param start  int for where to start sorting in this method (includes String at start)
     * @param end    int for where to end sorting in this method (does not include String at end)
     * @param index  the index of the character the method is currently sorting on
     **/
    private static void sortHelperMSD(String[] asciis, int start, int end, int index) {
        // Optional MSD helper method for optional MSD radix sorts
        return;
    }

    @Test
    public void testSort() {
        String[] test = {"a", "abc", "d", "k", "mdn",
            "jdc", "fa", "za", "az", "zzz", "aaa", "mqwd", "dcdcc", "qwd"};

        String[] sorted = sort(test);
        for (String s : test) {
            System.out.print(s + " ");
        }
        System.out.println();
        for (String s : sorted) {
            System.out.print(s + " ");
        }
    }
}
