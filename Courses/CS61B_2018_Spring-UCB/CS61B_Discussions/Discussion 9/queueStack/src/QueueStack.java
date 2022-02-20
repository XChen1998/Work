import org.junit.Test;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import static org.junit.Assert.*;

public class QueueStack<Type> {
    private Stack<Type> s = new Stack<>();

    public void push(Type x) {
        s.push(x);
    }

    public Type poll(){
        Stack<Type> temp = new Stack<>();
        while(!s.isEmpty()){
            temp.push(s.pop());
        }
        Type value = temp.pop();
        while(!temp.isEmpty()){
            s.push(temp.pop());
        }
        return value;
    }

    @Test
    public void testQueueStack(){
        QueueStack<Integer> test = new QueueStack<>();
        Queue<Integer> expected = new LinkedList<>();

        for (int i = 0; i < 10; i++){
            test.push(i);
            expected.add(i);
        }

        for (int i = 0; i < 10; i++){
            assertEquals(expected.poll(), test.poll());
        }
    }
}
