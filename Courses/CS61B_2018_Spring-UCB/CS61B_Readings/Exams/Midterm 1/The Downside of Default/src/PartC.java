public class PartC {
    /**
     * Non-destructively computes the sum of the given lists. Assumes
     * that all lists are of the same length and that none are null.
     */
    public static DLListOfInts sumOfLists(DLListOfInts[] lists) {
        if (lists.length == 0) {
            return null;
        }

        DLListOfInts result = new DLListOfInts();
        for (int i = 0; i < lists[0].size(); i++) {
            result.addLast(0);
        }

        for (int i = 0; i < lists.length; i++) {
            result.plusEquals(lists[i]);
        }
        return result;
    }
}