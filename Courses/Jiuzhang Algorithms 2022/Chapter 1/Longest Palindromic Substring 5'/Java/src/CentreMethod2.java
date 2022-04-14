public class CentreMethod2 {
    /**
     * @param s: input string
     * @return: a string as the longest palindromic substring
     */
    public String longestPalindrome(String s) {
        // write your code here
        if (s == null || s == "") {
            return "";
        }

        String longestPalindrome = "";

        for (int midPos = 0; midPos < s.length(); midPos++) {
            //odd case
            String oddLongestString = isPalindromic(s, midPos, midPos);
            //even case
            String evenLongestString = isPalindromic(s, midPos, midPos + 1);
            String longestStringAtM = oddLongestString.length() > evenLongestString.length() ? oddLongestString : evenLongestString;
            longestPalindrome = longestPalindrome.length() > longestStringAtM.length() ? longestPalindrome : longestStringAtM;
        }

        return longestPalindrome;

    }

    private String isPalindromic(String s, int leftPos, int rightPos) {
        while (leftPos >= 0 && rightPos < s.length()) {
            if (s.charAt(leftPos) != s.charAt(rightPos)) {
                break;
            }
            leftPos--;
            rightPos++;
        }
        return s.substring(leftPos + 1, rightPos);
    }
}