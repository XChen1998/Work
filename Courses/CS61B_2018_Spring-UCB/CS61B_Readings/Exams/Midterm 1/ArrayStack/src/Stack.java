import java.util.Arrays;

public interface Stack<Item> {
    void push(Item x); // places an item on “top” of the stack
    Item pop();        // removes and returns “top” item of the stack
    int size();        // returns the number of items on the stack
    public default void purge(Item x) { 
        ArrayStack<Item> newArrayStack = new ArrayStack<>();

        while(this.size() > 0){
            Item current = this.pop();
            if (!current.equals(x))
                newArrayStack.push(current);
        }
        while(newArrayStack.size() >0){
            this.push(newArrayStack.pop());
        }
    }

    public default void purgeElegant(Item x) {
        if(this.size() == 0){
            return;
        }
        Item top = this.pop();
        this.purge(x);
        if(!top.equals(x)){
            this.push(top);
        }
    }
}