public class OrderedTwoSum {
    public static boolean naiveFindSum(int[] A, int x) {
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < A.length; j++) {
                if (A[i] + A[j] == x) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean enhancedFindSum(int[] A, int x) {
        int left = 0;
        int right = A.length - 1;
        boolean isRun = true;
        while (isRun == true) {
            if (A[left] + A[right] == x) {
                return true;
            } else if (A[left] + A[right] < x) {
                left++;
            } else {
                right--;
            }
            if (left > right) {
                break;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int[] test = {1, 3, 4, 7, 8, 9, 12, 14, 15, 19, 21, 24, 27, 28, 29, 36};
        System.out.println(naiveFindSum(test, 29));
        System.out.println(enhancedFindSum(test, 29));
    }
}

