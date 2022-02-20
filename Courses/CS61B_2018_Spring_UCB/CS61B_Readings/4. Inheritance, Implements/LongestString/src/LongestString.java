import org.junit.Test;
public class LongestString {

    public static String longestString(String[] x) {
        if (x.length == 0) {
            return null;
        }
        String longest = x[0];
        for (int i = 0; i < x.length; i++) {
            if (x[i].length() > longest.length()) {
                longest = x[i];
            }
        }
        return longest;
    }

    @Test
    public void testLongestString() {
        String[] s = {"a", "cat", "always", "eats", "fish"};
        String longest = longestString(s);
        if (longest == "always") {
            System.out.println("Test passed.");
        } else {
            System.out.println("Expected: Always.");
            System.out.println("Actual: " + longest);
        }
    }
}