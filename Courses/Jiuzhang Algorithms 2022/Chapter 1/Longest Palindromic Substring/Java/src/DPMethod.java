public class DPMethod {
    /**
     * @param s: input string
     * @return: a string as the longest palindromic substring
     */
    public String longestPalindrome(String s) {
        if (s == null || s == "") {
            return "";
        }
        int longest = 1, start = 0;
        int sLength = s.length();
        boolean[][] isPalindrome = new boolean[sLength][sLength];
        for (int i = 0; i < sLength; i++) {
            isPalindrome[i][i] = true;
        }
        /*XChen: Dynamic programming cannot deal with string that has length 2!
         so we have to pre-process*/
        for (int i = 0; i < sLength - 1; i++) {
            isPalindrome[i][i + 1] = s.charAt(i) == s.charAt(i + 1);
            if (isPalindrome[i][i + 1]) {
                start = i;
                longest = 2;
            }
        }
        //for (int leftIndex = 0; leftIndex <= sLength - 2; leftIndex++) {
        /*XChen: Since you have to use the bottom left element to judge the top right element,
          you have to start from the bottom left to top right so that we can make everything
          reasonable.*/
        for (int leftIndex = sLength - 3; leftIndex >= 0; leftIndex--) {
            for (int rightIndex = leftIndex + 2; rightIndex < sLength; rightIndex++) {
                isPalindrome[leftIndex][rightIndex] = isPalindrome[leftIndex + 1][rightIndex - 1]
                        && (s.charAt(leftIndex) == s.charAt(rightIndex));
                if (isPalindrome[leftIndex][rightIndex] && rightIndex - leftIndex + 1 > longest) {
                    start = leftIndex;
                    longest = rightIndex - leftIndex + 1;
                }
            }
        }
        return s.substring(start, start + longest);
    }
}