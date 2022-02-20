import org.junit.Test;
import java.util.Random;
import java.util.Stack;

public class SortedStack<Type extends Comparable<Type>> {
    private Stack<Type> items = new Stack<>();

    public void push(Type i){
        Stack<Type> temp = new Stack<>();
        while (!items.isEmpty() && (items.peek().compareTo(i) < 0)){
            temp.push(items.pop());
        }
        items.push(i);
        while(!temp.isEmpty()){
            items.push(temp.pop());
        }
    }

    public Type poll(){
        /*Stack<Type> temp = new Stack<>();

        while (!items.isEmpty()){
            temp.push(items.pop());
        }
        Type value = temp.pop();
        while(!temp.isEmpty()){
            items.push(temp.pop());
        }
        return value;*/
        return items.pop();
    }

    @Test
    public void testSortedStack(){
        SortedStack<Integer> test = new SortedStack<>();
        Random r = new Random();
        for (int i = 0; i < 20; i++){
            test.push(r.nextInt(300));
        }
        for (int i = 0; i < 10; i++) {
            System.out.println(test.poll());
        }
    }
}
