# LeetCode & LintCode Notes

## [5. Longest Palindromic Substring](https://leetcode.com/problems/longest-palindromic-substring/) (Medium)

Key words: Centre Traverse Method, Dynamic Programming, String Problem, Two Pointer Method

### A. Centre Traverse Method

When we talk about how to judge if a substring is palindromic, we can use two pointer method. That is, from the start and end of the string and traverse the whole substring, or, alternatively, start from the middle of the substring, where we will have to consider both odd and even case. 

The lazy way is to check if every possible substring is palindromic and select the longest one. But the time complexity will be extremely bad for this case. Since we only want the longest one, here we introduce the *Centre Traverse Method*, where we traverse the array to set the midpoint and find the longest palindromic substring starting from this midpoint, as shown in the code below.

```Java
public class CentreMethod {
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
```

The time complexity of this method is $O(n^2)$, with a space complexity of $O(1)$. Please note that we still have to discuss the odd and even circumstances, respectively.

 

### B. Dynamic Programming Method

```Java
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
```

If we define a 2D `boolean` array to indicate the longest palindrome substring, he transition equation of this problem is as follows: 
$$
isPalindrome[leftIndex][rightIndex] = isPalindrome[leftIndex + 1][rightIndex - 1],
$$
if `s.charAt(leftIndex) == s.charAt(rightIndex)`, and upadate the start position and the longest number if `isPalindrome[leftIndex][rightIndex] && rightIndex - leftIndex + 1 > longest`. Finally, we return the substring of `s` from `start` to `start + longest`.



The time complexity of this method is still $O(n^2)$ since use nested for loops, but the space complexity grows to $O(n^2)$ due to the fact that a 2D `boolean` array is created for memory usage.



## [28. Implement strStr()](https://leetcode.com/problems/implement-strstr/) Easy

Key words: RK Algorithms, Traverse Method, String Problem

```
```

## [134. Gas Station](https://leetcode.com/problems/gas-station/) Medium

Key words: Traverse Method, Greedy Method

Greedy method linked question:

http://www.lintcode.com/en/problem/majority-number/
http://www.lintcode.com/en/problem/create-maximum-number/
http://www.lintcode.com/en/problem/jump-game-ii/
http://www.lintcode.com/en/problem/jump-game/
http://www.lintcode.com/en/problem/gas-station/
http://www.lintcode.com/en/problem/delete-digits/
http://www.lintcode.com/en/problem/linked-list-cycle-ii/
http://www.lintcode.com/en/problem/linked-list-cycle/

## [516. Longest Palindromic Subsequence](https://leetcode.com/problems/longest-palindromic-subsequence/) Medium

