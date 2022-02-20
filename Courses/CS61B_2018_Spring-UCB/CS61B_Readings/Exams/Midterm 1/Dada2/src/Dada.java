public class Dada {
    private static String[] rs;

    /**
     * Prints out the given array, i.e. if d contains two Strings
     * with names "alice" and "bob", this method will print "alice bob ".
     */
    private static void printStringArray(String[] s) {
        for (int i = 0; i < s.length; i += 1) {
            System.out.print(s[i] + " ");
        }
        System.out.println();
    }

    private static void fillMany(String[] d) {
        System.arraycopy(Dada.rs, 0, d, 0, d.length);
    }

    private static void fillOne(String d) {
        d = Dada.rs[0];
    }

    public static void main(String[] args) {
        String a = "alice";
        String b = "bob";
        String c = "carol";
        String d = "dan";
        String[][] twod = {{a, b}, {c, d}};
        Dada.rs = new String[]{"fritz", "gritz"};
        String[] X = twod[0];
        printStringArray(X);
        fillOne(X[0]);
        printStringArray(X);
        fillMany(X);
        printStringArray(X);
    }
}