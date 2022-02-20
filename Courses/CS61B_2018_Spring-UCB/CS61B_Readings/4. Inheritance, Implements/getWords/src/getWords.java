import java.util.*;

public class getWords {
    private static String file = "words.txt";

    public static String cleanString(String s) {
        String a = s.toLowerCase().replaceAll("[^a-z]]", "");
        return a.replaceAll(",", "");
    }

    public static List<String> getWordsFunc(String filename) {
        List<String> words = new ArrayList<String>();
        In in = new In(filename);
        while (!in.isEmpty()) {
            String nextWord = cleanString(in.readString());
            words.add(nextWord);
        }
        return words;
    }

    public static int countUniqueWords(List<String> words) {
        Set<String> wordSet = new HashSet<String>();
/*        for (int i =0 ; i< words.size(); i++){
            String ithWord = words.get(i);
        }*/

        for (String ithWord : words) { /*This syntax is very much like *in*  in Python*/
            wordSet.add(ithWord);
        }
        return wordSet.size();
    }

    public static Map<String, Integer> collectWordCount(List<String> words, List<String> targets) {
        Map<String, Integer> counts = new HashMap<String, Integer>();
        /*We have not seen any of the words*/
        for (String t : targets) {
            counts.put(t, 0);
        }

        for (String s : words) {
            if (counts.containsKey(s)) {
                int oldCount = counts.get(s);
                /*Cannot use oldCount++, cuz this happens last*/
                counts.put(s, oldCount + 1);
            }
        }
        return counts;
    }

    public static void main(String[] args) {
        List<String> words = getWordsFunc(file);
        System.out.println(words);
        System.out.println(words.size());
        System.out.println(countUniqueWords(words));

        List<String> targets = new ArrayList<>();
        targets.add("the");
        targets.add("women");
        targets.add("that");

        Map<String, Integer> count = collectWordCount(words, targets);
        System.out.println(count);
    }
}
