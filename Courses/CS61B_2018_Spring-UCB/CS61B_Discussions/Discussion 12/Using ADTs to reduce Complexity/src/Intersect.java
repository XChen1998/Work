import java.util.HashSet;

public class Intersect {
    public static int[] intersectArray(int[] A, int[] B){
        HashSet<Integer> all = new HashSet<>();
        HashSet<Integer> intersect = new HashSet<>();
        for (int x : A){
            all.add(x);
        }
        for (int x : B){
            if(all.contains(x)){
                intersect.add(x);
            }
        }
        int index = 0;
        int[] returnedArray = new int[intersect.size()];
        for (int x : intersect){
            returnedArray[index] = x;
            index++;
        }
        return returnedArray;
    }
}
