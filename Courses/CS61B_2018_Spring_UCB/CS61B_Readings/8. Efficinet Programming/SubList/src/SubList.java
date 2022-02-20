import java.util.ArrayList;
import java.util.List;

public class SubList {

    public static void main(String[] args) {
        /** Create an ArrayList. */
        List<String> L = new ArrayList<>();
        /** Add some items. */
        L.add("at");
        L.add("ax");
        L.add("ban");
        L.add("bat");
        L.add("cat");
        /** subList me up fam. */
        List<String> SL = L.subList(1, 4);
        /** Mutate that thing. */
        SL.set(0, "jug");
    }
}
