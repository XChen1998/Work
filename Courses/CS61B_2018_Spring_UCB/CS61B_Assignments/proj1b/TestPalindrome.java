import org.junit.Test;

import static org.junit.Assert.*;

public class TestPalindrome {
    // You must use this palindrome, and not instantiate
    // new Palindromes, or the autograder might be upset.
    static Palindrome palindrome = new Palindrome();

    @Test
    public void testWordToDeque() {
        Deque d = palindrome.wordToDeque("persiflage");
        String actual = "";
        for (int i = 0; i < "persiflage".length(); i++) {
            actual += d.removeFirst();
        }
        assertEquals("persiflage", actual);
    }
    // Uncomment this class once you've created your Palindrome class.

    @Test
    public void testShortDeque() {
        String a = "";
        String b = "a";
        boolean actualA = palindrome.isPalindrome(a);
        boolean actualB = palindrome.isPalindrome(b);
        assertTrue(actualA);
        assertTrue(actualB);
    }

    @Test
    public void testFalsePalindrome() {
        String a = "1!13";
        String b = "Abafda";
        assertFalse(palindrome.isPalindrome(a));
        assertFalse(palindrome.isPalindrome(b));
    }

    @Test
    public void testTruePalindrome() {
        String a = "!noon!";
        String b = "A123321A";
        assertTrue(palindrome.isPalindrome(a));
        assertTrue(palindrome.isPalindrome(b));
    }

    @Test
    public void testShortDequeNew() {
        OffByOne obo = new OffByOne();
        String a = "a";
        String b = " ";
        boolean actualA = palindrome.isPalindrome(a, obo);
        boolean actualB = palindrome.isPalindrome(b, obo);
        assertTrue(actualA);
        assertTrue(actualB);
    }

    @Test
    public void testFalsePalindromeNew() {
        OffByOne obo = new OffByOne();
        String a = "1242";
        String b = "aba";
        assertFalse(palindrome.isPalindrome(a, obo));
        assertFalse(palindrome.isPalindrome(b, obo));
    }

    @Test
    public void testFalselongPalindromeNew() {
        OffByOne obo = new OffByOne();
        String a = "!noon!";
        String b = "A123321A";
        assertFalse(palindrome.isPalindrome(a, obo));
        assertFalse(palindrome.isPalindrome(b, obo));
    }
}
