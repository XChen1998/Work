import java.util.HashSet;

public class Union {
    public static int[] unionArray(int[] A, int[] B) {
        HashSet<Integer> set = new HashSet<>();
        for (int x : A) {
            set.add(x);
        }
        for (int x : B) {
            set.add(x);
        }
        int[] returnedArray = new int[set.size()];
        int index = 0;
        for (int x : set){
            returnedArray[index] = x;
            index++;
        }
        return returnedArray;
    }
}
