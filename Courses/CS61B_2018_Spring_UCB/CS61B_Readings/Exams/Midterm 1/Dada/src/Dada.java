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

    public static void main(String[] args) {
        String a = "alice";
        String b = "bob";
        String c = "carol";
        String d = "dan";
        String[][] twod = {{a, b}, {c, d}};
        String[] X = twod[1];
        printStringArray(X); //____________________________
        Dada.rs = X;
        String[] Y = Dada.rs;
        Y = new String[]{d, c};
        Dada.rs[1] = "eve";
        printStringArray(Dada.rs); //____________________________
        printStringArray(Y); //____________________________
        printStringArray(twod[0]); //____________________________
        printStringArray(twod[1]); //____
    }
}