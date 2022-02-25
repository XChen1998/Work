package hw3.hash;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class OomageTestUtility {
    public static boolean haveNiceHashCodeSpread(List<Oomage> oomages, int M) {
        /* TODO:
         * Write a utility function that returns true if the given oomages
         * have hashCodes that would distribute them fairly evenly across
         * M buckets. To do this, convert each oomage's hashcode in the
         * same way as in the visualizer, i.e. (& 0x7FFFFFFF) % M.
         * and ensure that no bucket has fewer than N / 50
         * Oomages and no bucket has more than N / 2.5 Oomages.
         */
        HashMap<Integer, Integer> bucketNum = new HashMap<>();
        for (Oomage cur : oomages) {
            int hashcodeNum = (cur.hashCode() & 0x7FFFFFFF) % M;
            if (bucketNum.containsKey(hashcodeNum)) {
                int currentValue = bucketNum.get(hashcodeNum);
                bucketNum.put(hashcodeNum, currentValue + 1);
            } else {
                bucketNum.put(hashcodeNum, 1);
            }
        }
        Set<Integer> N = bucketNum.keySet();
        for (int cur : N) {
            int number = bucketNum.get(cur);
            if (number < oomages.size() / 50 || number > oomages.size() / 2.5) {
                return false;
            }
        }
        return true;
    }
}
