import org.junit.Test;

import java.util.LinkedList;

public class StackLinkedList_two<Item> {
    private LinkedList<Item> items = new LinkedList<>();
    public void push(Item x){
        items.add(x);
    }

    @Test
    public void testStackLinkedList_two() {
        StackLinkedList_two<Integer> test = new StackLinkedList_two<>();
        for (int i = 0; i < 100; i++) {
            test.push(i);
        }
    }
}
