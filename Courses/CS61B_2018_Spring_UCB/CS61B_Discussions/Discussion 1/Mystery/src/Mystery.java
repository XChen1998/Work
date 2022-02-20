import java.util.Arrays;

public class Mystery {
    /* This function should return the index of the smallest number start from k. */

    public static int mystery(int[] inputArray, int k) {
        int x = inputArray[k];
        int answer = k;
        int index = k + 1;
        while (index < inputArray.length) {
            if (inputArray[index] < x) {
                x = inputArray[index];
                answer = index;
            }
            index = index + 1;
        }
        return answer;
    }

    /* Sort the array from small to large, destructively */
    public static void mystery2(int[] inputArray) {
        int index = 0;
        while (index < inputArray.length) {
            /* targetIndex = index of the smallest element starting from 0 */
            /* This is a selection sort algorithm */
            int targetIndex = mystery(inputArray, index);
            int temp = inputArray[targetIndex];
            inputArray[targetIndex] = inputArray[index];
            inputArray[index] = temp;
            index = index + 1;
        }
    }

    public static void main(String[] args){
        int[] x = {1, 3, 9, 4, 5, 8};
        System.out.println(mystery(x , 2));
        mystery2(x);
        System.out.println(Arrays.toString(x));

    }



}
