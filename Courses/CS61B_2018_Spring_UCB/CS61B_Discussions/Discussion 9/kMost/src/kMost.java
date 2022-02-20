import org.junit.Test;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class kMost {

    public static void topkPopularWords(String[] words, int k) {
        Map<String, Integer> wordlist = new HashMap<>();
        for (String current : words) {
            if (wordlist.containsKey(current)) {
                wordlist.put(current, wordlist.get(current) + 1);
            } else {
                wordlist.put(current, 1);
            }
        }

        PriorityQueue<String> list = new PriorityQueue<>(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return -wordlist.get(o1) + wordlist.get(o2);
            }
        });

        for (String current : wordlist.keySet()) {
            list.add(current);
        }

        for (int i = 0; i < k; i++) {
            System.out.println(list.poll());
        }
    }

    @Test
    public void testkMost() {
        String[] testlist = {"3", "3", "3", "3", "3", "1", "a",
                "b", "c", "d", "a", "1", "2", "2", "2", "b", "b" };
        kMost.topkPopularWords(testlist, 3);
    }
}
