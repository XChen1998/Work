import org.junit.Test;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;

public class NumListQuestion {
    public static int[] removeDuplicates(int[] arr){
        HashSet<Integer> set = new HashSet<>();
        final int len = arr.length;
        //changed end to len
        for(int i = 0; i < len; i++){
            set.add(arr[i]);
        }
        int[] whitelist = new int[set.size()];
        int i = 0;
        for (Iterator<Integer> it = set.iterator(); it.hasNext();) {
            whitelist[i++] = it.next();
        }
        return whitelist;
    }

    /*The naive method of runtime complexity N.*/
    public static int f1(int i, int[] numList) {
        for (int j = 0; j < numList.length; j++) {
            // System.out.println(j);
            if (numList[j] == i) {
                return j;
            }
        }
        System.out.println("f1 Item not found.");
        return -1;
    }

    public static int f2(int i, int[] numList) {
        return f2(i, numList, 0, numList.length - 1);
    }

    /*Private helper method.*/
    private static int f2(int i, int[] numList, int start, int end) {
        /*The base case.*/
        if (end - start == 1) {
            if (numList[start] == i) {
                return start;
            } else if (numList[end] == i) {
                return end;
            } else {
                System.out.println("f2 Item not found.");
                return -1;
            }
        }
        /*Recursively find the position.*/
        int mid = start + (end - start) / 2;
        if (numList[mid] > i) {
            return f2(i, numList, start, end - (end - start) / 2);
        } else if (numList[mid] < i) {
            return f2(i, numList, start + (end - start) / 2, end);
        } else {
            return mid;
        }
    }

    /*The testing function.*/
    @Test
    public void testFindQuestion() {
        Random random = new Random();
        int[] numList = new int[200];
        for (int i = 0; i < numList.length; i++) {
            numList[i] = random.nextInt(200);
            ;
        }
        Arrays.sort(numList);
        numList = removeDuplicates(numList);
        for (int i = 0; i < numList.length; i++) {
            System.out.println("------------------");
            System.out.println("Round:" + i);
            System.out.println("Finding: " + numList[i]);
            org.junit.Assert.assertEquals(f1(numList[i], numList), f2(numList[i], numList));
            int randomNum = random.nextInt(200);
            System.out.println("Finding: " + randomNum);
            org.junit.Assert.assertEquals(f1(randomNum, numList), f2(randomNum, numList));
            System.out.println("------------------");
        }
    }
}
