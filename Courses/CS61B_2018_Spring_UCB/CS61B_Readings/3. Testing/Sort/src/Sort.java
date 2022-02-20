public class Sort {
    /**
     * Sorts strings destructively.
     */
    public static void sort(String[] x) {
        // find the smallest item
        // move it to the front
        // selection sort the rest (using recursion?)
        sort(x, 0);

    }

    //* Sorts x stating at position start */
    private static void sort(String[]x, int start){
        if (start == x.length){
            return;
        }
        int smallestIndex = findSmallest(x, start);
        swap(x, start, smallestIndex);
        sort(x, start + 1);
    }

    /**
     * Returns the smallest string index in x.
     */
    public static int findSmallest(String[] x, int start) {
        int smallest = start;
        for (int i = start; i < x.length; i += 1) {
            int cmp = x[i].compareTo(x[smallest]);
            if (cmp < 0) {
                smallest = i;
            }
        }
        return smallest;
    }

    public static void swap(String[] x, int a, int b){
        String cur = x[a];
        x[a] = x[b];
        x[b] = cur;
    }
}
