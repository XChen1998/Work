import org.junit.Test;

public class testSkippify {

    @Test
    public void testSkippify() {
        IntList test = new IntList(0, null);
        for (int i = 1; i < 11; i++) {
            test.addFirst(11 - i);
        }
        test.skippify();
    }
}
