public class MyStupidSolution {
    /**
     * @param s: the maximum length of s is 1000
     * @return: the longest palindromic subsequence's length
     */

    /**
     * XChen: This code will not work as it only delete continuous
     * substring and check the rest
     * */
    public int longestPalindromeSubseq(String s) {
        if (s == null || s == "") {
            return 0;
        }
        int sLength = s.length();
        if (isPalindromic(s)) {
            return sLength;
        }
        for (int length = sLength - 1; length > 1; length--) {
            int deletedNum = sLength - length;
            for (int startPos = 0; startPos < sLength - deletedNum; startPos++) {
                String leftString = s.substring(0, startPos);
                String rightString = s.substring(startPos + deletedNum, sLength);
                String curString = leftString + rightString;
                if (isPalindromic(curString)) {
                    return length;
                }
            }
        }
        return 1;
    }

    private boolean isPalindromic(String s) {
        int leftPos = 0;
        int rightPos = s.length() - 1;
        boolean flag = true;
        while (leftPos <= rightPos) {
            if (s.charAt(leftPos) != s.charAt(rightPos)) {
                flag = false;
                break;
            }
            leftPos++;
            rightPos--;
        }
        return flag;
    }

}
