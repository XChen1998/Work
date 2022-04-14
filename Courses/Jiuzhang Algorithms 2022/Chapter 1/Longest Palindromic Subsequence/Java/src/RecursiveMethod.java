public class RecursiveMethod {
    /**
     * @param s: the maximum length of s is 1000
     * @return: the longest palindromic subsequence's length
     */

    /**
     * XChen: The efficiency of this method is highly dependent on the input,
     * the reason behind is that we do not know */


    public int longestPalindromeSubseq(String s) {
        if (s == null || s.equals("")) {
            return 0;
        }
        int sLength = s.length();
        int[][] memory = new int[sLength][sLength];

        for (int[] row: memory) {
            for (int i = 0; i < sLength; i++) {
                row[i] = -1;
            }
        }

        return isPalindromeSubseq(s, 0, sLength - 1, memory);
    }

    public int isPalindromeSubseq(String s, int left, int right, int[][] memory) {
        int length;
        // stop condition
        if (memory[left][right] != -1) {
            return memory[left][right];
        }
        if (left == right){
            return 1;
        }
        if (left > right){
            return 0;
        }
        //recursion
        if (s.charAt(left) == s.charAt(right)) {
            length = isPalindromeSubseq(s, left + 1, right - 1, memory) + 2;
        } else {
            length = Math.max(isPalindromeSubseq(s, left, right - 1, memory), isPalindromeSubseq(s, left + 1, right, memory));
        }
        memory[left][right] = length;
        return length;
    }
}