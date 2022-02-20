import org.junit.Test;

import static org.junit.Assert.*;

public class TestOffByN {
    @Test
    public void testOffByNTrue() {
        CharacterComparator obn = new OffByN(2);
        char a = 'a';
        char b = 'c';
        assertTrue(obn.equalChars(a, b));
    }

    @Test
    public void testOffByNFalse() {
        CharacterComparator obn = new OffByN(1);
        char a = '1';
        char b = '4';
        assertFalse(obn.equalChars(a, b));
    }
}
