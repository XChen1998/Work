import org.junit.Test;
import java.util.*;

public class Array {

    // Iteration method
    public static int[] insert(int[] arr, int item, int position) {
        int[] newArray = new int[arr.length + 1];
        position = Math.min(arr.length, position);
        for (int i = 0; i < newArray.length; i++) {
            if (i == position) {
                newArray[i] = item;
            } else {
                if (i < position)
                    newArray[i] = arr[i];
                else {
                    if (i > position) {
                        newArray[i] = arr[i - 1];
                    }
                }
            }
        }
        return newArray;
    }

    //Stytem.arraycopymethod
    public static int[] insertSystem(int[] arr, int item, int position) {
        int[] newArray = new int[arr.length + 1];
        position = Math.min(arr.length, position);
        System.arraycopy(arr, 0, newArray, 0, position);
        System.arraycopy(arr, position, newArray, position + 1, arr.length - position);
        newArray[position] = item;
        return newArray;
    }

    @Test
    public void testInsertMethod() {
        int[] array1 = {1, 3, 5, 7};
        int[] array2 = {1, 3, 5, 7};
        for (int i = 0; i < 15; i++) {
            int position = (int) (Math.random() * array1.length * 2);
            int item = (int) ((Math.random()) * 100);
            System.out.print(item + ", ");
            array1 = insert(array1, item, position);
            array2 = insertSystem(array2, item, position);
        }
        System.out.println();
        System.out.println(Arrays.toString(array1));
        System.out.println(Arrays.toString(array2));
        org.junit.Assert.assertArrayEquals(array1, array2);
    }

    private void swap(int[] arr, int a, int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }

    public void swap(int[] arr) {
        for (int i = 0; i < arr.length / 2; i++) {
            swap(arr, i, arr.length - 1 - i);
        }
    }

    @Test
    public void testSwap() {
        int[] test = new int[100];
        int[] expected = new int[100];
        for (int i = 0; i < 100; i++) {
            test[i] = i;
            expected[i] = 100 - i - 1;
        }

        int[] test2 = new int[101];
        int[] expected2 = new int[101];
        for (int i = 0; i < 101; i++) {
            test2[i] = i;
            expected2[i] = 101 - i - 1;
        }
        swap(test);
        swap(test2);
        System.out.println(Arrays.toString(test));
        System.out.println(Arrays.toString(expected));
        System.out.println(Arrays.toString(test2));
        System.out.println(Arrays.toString(expected2));
        org.junit.Assert.assertArrayEquals(test, expected);
        org.junit.Assert.assertArrayEquals(test2, expected2);
    }

    private static int sum(int[] arr) {
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
        }
        return sum;
    }

    public static int[] replicate(int[] arr) {
        int[] replicated = new int[sum(arr)];
        int position = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i]; j++) {
                replicated[position] = arr[i];
                position += 1;
            }
        }
        return replicated;
    }

    @Test
    public void testReplicate() {
        int[] original = {2, 1, 3, 5, 4, 0};
        int[] expected = {2, 2, 1, 3, 3, 3, 5, 5, 5, 5, 5, 4, 4, 4, 4};
        int[] replicated = replicate(original);
        System.out.println(Arrays.toString(replicated));
        org.junit.Assert.assertArrayEquals(replicated, expected);
    }


}
