import java.util.Random;

public class MergeSort {

    /*Merge two sorted array.*/
    private static int[] merge(int[] A, int[] B) {
        int[] merged = new int[A.length + B.length];
        int posA = 0;
        int posB = 0;
        for (int i = 0; i < merged.length; i++) {
            if (posB < B.length) {
                if (A[posA] > B[posB]) {
                    merged[i] = B[posB];
                    posB++;
                    if (posB == B.length) {
                        System.arraycopy(A, posA, merged, i + 1, merged.length - i - 1);
                        return merged;
                    }
                } else {
                    merged[i] = A[posA];
                    posA++;
                    if (posA == A.length) {
                        System.arraycopy(B, posB, merged, i + 1, merged.length - i - 1);
                        return merged;
                    }
                }
            }
        }
        return merged;
    }

    /*Sort an array recursively.*/
    public static int[] sort(int[] A) {
        if (A.length == 1) {
            return A;
        }
        int cutPoint = A.length / 2;
        int[] x = new int[cutPoint];
        int[] y = new int[A.length - cutPoint];
        System.arraycopy(A, 0, x, 0, cutPoint);
        System.arraycopy(A, cutPoint, y, 0, A.length - cutPoint);
        return merge(sort(x), sort(y));
    }

    public static void print(int[] x) {
        for (int i = 0; i < x.length; i++) {
            System.out.print(x[i]);
            System.out.print(' ');
        }
    }

    /*Test the MergeSort method*/
    public static void main(String[] args) {
        Random random = new Random();
        int[] unsorted = new int[100];
        for (int i = 0; i < 100; i++) {
            unsorted[i] = random.nextInt(1000);
        }
        int[] sorted = sort(unsorted);
        print(sorted);
    }
}
