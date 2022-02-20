import org.junit.Test;

import java.util.LinkedList;

public class StackLinkedList_one<Item> extends LinkedList<Item> {
    public void push(Item x) {
        add(x);
    }

    @Test
    public void testStackLinkedList_one() {
        StackLinkedList_one<Integer> test = new StackLinkedList_one<>();
        for (int i = 0; i < 100; i++) {
            test.push(i);
        }
    }
}

