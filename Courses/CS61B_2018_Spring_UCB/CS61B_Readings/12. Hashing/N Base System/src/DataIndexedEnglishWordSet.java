public class DataIndexedEnglishWordSet {
    private boolean[] present;

    public DataIndexedEnglishWordSet() {
        present = new boolean[2000000000];
    }

    public void add(String s) {
        present[englishToInt(s)] = true;
    }

    public boolean contains(String s) {
        return present[englishToInt(s)];
    }

    public static int letterNum(String s, int i) {
        /** Converts ith character of String to a letter number.
         * e.g. 'a' -> 1, 'b' -> 2, 'z' -> 26 */
        int ithChar = s.charAt(i);
        if ((ithChar < 'a') || (ithChar > 'z')) {
            throw new IllegalArgumentException();
        }
        return ithChar - 'a' + 1;
    }

    public static int englishToInt(String s) {
        int intRep = 0;
        for (int i = 0; i < s.length(); i += 1) {
            intRep = intRep * 26;
            intRep += letterNum(s, i);
        }

        return intRep;
    }

    public static void main(String[] args) {
        DataIndexedEnglishWordSet test = new DataIndexedEnglishWordSet();
        test.add("ab");
        System.out.println(DataIndexedEnglishWordSet.englishToInt("ab"));
    }
}