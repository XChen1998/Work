public class Runtime2 {
    public static boolean mystery(int[] array) {
        array = MergeSort.sort(array);
        int N = array.length;
        for (int i = 0; i < N; i += 1) {
            boolean x = false;
            for (int j = 0; j < N; j += 1) {
                if (i != j && array[i] == array[j])
                    x = true;
            }
            if (!x) {
                return false;
            }
        }
        return true;
    }
}
