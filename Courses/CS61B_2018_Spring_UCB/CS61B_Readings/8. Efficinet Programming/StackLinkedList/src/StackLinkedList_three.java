import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class StackLinkedList_three<Item> {
    private List<Item> L;
    public StackLinkedList_three(List<Item> worker) {
        L = worker;
    }

    public void push(Item x) {
        L.add(x);
    }

    public void clean(){
        L.clear();
    }

    public void removeElement(Item o){
        L.remove(o);
    }

    public static void main(String[] args) {
        List<Integer> stuff = new ArrayList<>();
        StackLinkedList_three<Integer> test = new StackLinkedList_three(stuff);
        for (int i = 0; i< 5; i++){
            test.push(i);
        }

        List<Integer> stuff_2 = new LinkedList<>();
        StackLinkedList_three<Integer> test_2 = new StackLinkedList_three(stuff_2);
        for (int i = 0; i< 5; i++){
            test_2.push(i);
        }
        test_2.removeElement(3);
        test_2.clean();

    }
}