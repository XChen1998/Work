/**
 * Class with 2 ways of doing Counting sort, one naive way and one "better" way
 *
 * @author Akhil Batra, Alexander Hwang
 **/
public class CountingSort {
    /**
     * Counting sort on the given int array. Returns a sorted version of the array.
     * Does not touch original array (non-destructive method).
     * DISCLAIMER: this method does not always work, find a case where it fails
     *
     * @param arr int array that will be sorted
     * @return the sorted array
     */
    public static int[] naiveCountingSort(int[] arr) {
        // find max
        int max = Integer.MIN_VALUE;
        for (int i : arr) {
            max = max > i ? max : i;
        }

        // gather all the counts for each value
        int[] counts = new int[max + 1];
        for (int i : arr) {
            counts[i]++;
        }

        // when we're dealing with ints, we can just put each value
        // count number of times into the new array
        int[] sorted = new int[arr.length];
        int k = 0;
        for (int i = 0; i < counts.length; i += 1) {
            for (int j = 0; j < counts[i]; j += 1, k += 1) {
                sorted[k] = i;
            }
        }

        // however, below is a more proper, generalized implementation of
        // counting sort that uses start position calculation
        int[] starts = new int[max + 1];
        int pos = 0;
        for (int i = 0; i < starts.length; i += 1) {
            starts[i] = pos;
            pos += counts[i];
        }

        int[] sorted2 = new int[arr.length];
        for (int i = 0; i < arr.length; i += 1) {
            int item = arr[i];
            int place = starts[item];
            sorted2[place] = item;
            starts[item] += 1;
        }

        // return the sorted array
        return sorted2;
    }

    /**
     * Counting sort on the given int array, must work even with negative numbers.
     * Note, this code does not need to work for ranges of numbers greater
     * than 2 billion.
     * Does not touch original array (non-destructive method).
     *
     * @param arr int array that will be sorted
     */

    /*XChen: This method is pretty lazy. It is easy but not very fast :(*//*
    public static int[] betterCountingSortLazy(int[] arr) {
        int negtiveNum = 0;
        int nonNegativeNum = 0;
        for (int i : arr) {
            if (i < 0) {
                negtiveNum += 1;
            } else {
                nonNegativeNum += 1;
            }
        }
        if (negtiveNum == 0) {
            return naiveCountingSort(arr);
        } else {
            int[] negativePart = new int[negtiveNum];
            int[] nonNegativePart = new int[nonNegativeNum];
            int curNegativePos = 0;
            int curNonNegativePos = 0;
            for (int i : arr) {
                if (i < 0) {
                    negativePart[curNegativePos] = -i;
                    curNegativePos++;
                } else {
                    nonNegativePart[curNonNegativePos] = i;
                }

            }
            int[] sortedReverseNegativePart = naiveCountingSort(negativePart);
            int[] sortedNegativePart = new int[sortedReverseNegativePart.length];
            for (int i = 0; i < sortedReverseNegativePart.length; i++) {
                sortedNegativePart[sortedNegativePart.length - i - 1] =
                        -sortedReverseNegativePart[i];
            }
            int[] sortedNonNegativePart = naiveCountingSort(nonNegativePart);
            int[] returned = new int[sortedNegativePart.length + sortedNonNegativePart.length];
            int curReturnedPos = 0;
            for (int i : sortedNegativePart) {
                returned[curReturnedPos] = i;
                curReturnedPos++;
            }
            for (int i : sortedNonNegativePart) {
                returned[curReturnedPos] = i;
                curReturnedPos++;
            }
            return returned;
        }
    }*/
    public static int[] betterCountingSort(int[] arr) {
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        for (int i : arr) {
            max = max > i ? max : i;
            min = min < i ? min : i;
        }
        int[] counts = new int[max - min + 1];

        for (int i : arr) {
            counts[i - min]++;
        }

        int[] pos = new int[max - min + 1];
        int cumulativePos = 0;
        for (int i = 0; i < counts.length; i++) {
            pos[i] = cumulativePos;
            cumulativePos += counts[i];
        }

        int[] sorted = new int[arr.length];
        for (int i : arr) {
            sorted[pos[i - min]] = i;
            pos[i - min]++;
        }

        return sorted;
    }

}
