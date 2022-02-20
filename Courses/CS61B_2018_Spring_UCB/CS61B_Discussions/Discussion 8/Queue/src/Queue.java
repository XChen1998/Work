import java.util.Stack;

public class Queue<E> {
    private Stack<E> stack = new Stack<>();

    public void push(E element){
        stack.push(element);
    }

    public E pop(){
        Stack<E> temp = new Stack<>();
        while(!stack.isEmpty()){
            temp.push(stack.pop());
        }
        E toTop = temp.pop();
        while(!temp.isEmpty()){
            stack.push(temp.pop());
        }
        return toTop;
    }


}
