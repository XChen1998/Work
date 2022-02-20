import org.junit.Test;

import static org.junit.Assert.*;

public class TestOffByOne {
    // You must use this CharacterComparator and not instantiate
    // new ones, or the autograder might be upset.
    static CharacterComparator offByOne = new OffByOne();

    // Your tests go here.
    // Uncomment this class once you've created your
    // CharacterComparator interface and OffByOne class.
    @Test
    public void testEqualCharsFalse() {
        char a = '!';
        char b = '5';
        assertFalse(offByOne.equalChars(a, b));
    }

    @Test
    public void testEqualCharsTrue() {
        char a = '1';
        char b = '2';
        assertTrue(offByOne.equalChars(a, b));
    }

    @Test
    public void testEqualCharZero() {
        char a = ' ';
        char b = ' ';
        assertFalse(offByOne.equalChars(a, b));
    }

    @Test
    public void testEqualCharDot() {
        char a = '.';
        char b = '.';
        assertFalse(offByOne.equalChars(a, b));
    }

    @Test
    public void testEqualCharAlphabet() {
        char a = 'a';
        char b = 'b';
        assertTrue(offByOne.equalChars(a, b));
    }

    @Test
    public void testEqualCharUperlower() {
        char a = 'A';
        char b = 'b';
        assertFalse(offByOne.equalChars(a, b));
    }

}
