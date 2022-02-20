import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class IteratorExample {
    public static void main(String[] args) {
        List<Integer> friends = new ArrayList<>();
        friends.add(5);
        friends.add(6);
        friends.add(7);
        Iterator<Integer> seer = friends.iterator();

        while (seer.hasNext()) {
            System.out.println(seer.next());
        }
    }
}



