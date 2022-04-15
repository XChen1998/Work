public class DPVersion1dot1 {
    /**
     * @param s: the maximum length of s is 1000
     * @return: the longest palindromic subsequence's length
     */
    public int longestPalindromeSubseq(String s) {
        if (s == null || s == "") {
            return 0;
        }
        int sLength = s.length();
        char[] sChar = s.toCharArray();
        int [][] isPalindromic = new int[sLength][sLength];
        for (int i = 0 ; i < sLength; i++) {
            isPalindromic[i][i] = 1;
        }

        for (int leftPos = sLength - 1; leftPos >= 0; leftPos--) {
            for (int rightPos = leftPos + 1; rightPos < sLength; rightPos++) {
                if (sChar[leftPos] == sChar[rightPos]) {
                    isPalindromic[leftPos][rightPos] = isPalindromic[leftPos + 1][rightPos - 1] + 2;
                } else {
                    isPalindromic[leftPos][rightPos] = Math.max(isPalindromic[leftPos + 1][rightPos],
                            isPalindromic[leftPos][rightPos - 1]);
                }
            }
        }
        return isPalindromic[0][sLength - 1];
    }
}