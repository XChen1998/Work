public class QuikMaths {
    public static void multiplyBy3(int[] A) {
        for (int x : A) {
            x = x * 3; // Here x is pass by value, so this statement cannot change the values in A
        }
    }

    public static void multiplyBy2(int[] A) {
        int[] B = A;
        for (int i = 0; i < B.length; i += 1) {
            B[i] *= 2;
        }
    }

    public static void multiplyBy22(int[] A) {
        for (int i = 0; i < A.length; i += 1) {
            A[i] *= 2;
        }
    }

    public static void swap(int A, int B) {
        int temp = B;
        B = A;
        A = temp;
    }

    public static void main(String[] args) {
        int[] arr;
        arr = new int[]{2, 3, 3, 4};
        multiplyBy3(arr); // This function is void.

        /*Valueofarr:{2,3,3,4}*/


        multiplyBy2(arr); // arr -> {4, 6, 6, 8}

        arr = new int[]{2, 3, 3, 4};
        multiplyBy22(arr); // arr -> {4, 6, 6, 8}

        /*Valueofarr:{4,6,6,8}*/

        int a = 6;
        int b = 7;
        swap(a, b); // a & b is pass by value, it cannot be changed.

        /*Valueofa:6 Valueofb:7*/
    }
}