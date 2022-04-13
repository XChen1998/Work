public class MySolution {
    /**
     * @param s: input string
     * @return: a string as the longest palindromic substring
     */
    public String longestPalindrome(String s) {
        // write your code here
        if (s == null || s == "") {
            return "";
        }
        if (s.length() == 1) {
            return s;
        }

        int longestPalindromeLength = 0;
        int leftPos = 0;
        int rightPos = 0;
        // odd case
        for (int midPos = 0; midPos < s.length(); midPos++) {
            int curLeftPos = midPos;
            int curRightPos = midPos;
            while (curLeftPos >= 0 && curRightPos < s.length()) {
                if (s.charAt(curLeftPos) != s.charAt(curRightPos)) {
                    break;
                }
                if ((s.charAt(curLeftPos) == s.charAt(curRightPos))
                        && (curRightPos - curLeftPos + 1) > longestPalindromeLength) {
                    longestPalindromeLength = curRightPos - curLeftPos + 1;
                    leftPos = curLeftPos;
                    rightPos = curRightPos;
                }
                curLeftPos--;
                curRightPos++;
            }
        }
        // even case
        for (int midPos = 0; midPos < s.length(); midPos++) {
            int curLeftPos = midPos;
            int curRightPos = midPos + 1;
            while (curLeftPos >= 0 && curRightPos < s.length()) {
                if (s.charAt(curLeftPos) != s.charAt(curRightPos)) {
                    break;
                }
                if ((s.charAt(curLeftPos) == s.charAt(curRightPos))
                        && (curRightPos - curLeftPos + 1) > longestPalindromeLength) {
                    longestPalindromeLength = curRightPos - curLeftPos + 1;
                    leftPos = curLeftPos;
                    rightPos = curRightPos;
                }
                curLeftPos--;
                curRightPos++;
            }
        }
        return s.substring(leftPos, rightPos + 1);

    }
}