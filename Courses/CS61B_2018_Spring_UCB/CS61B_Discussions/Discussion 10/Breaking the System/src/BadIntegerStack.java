public class BadIntegerStack {
    public class Node {
        public Integer value;
        public Node prev;

        public Node(Integer v, Node p) {
            value = v;
            prev = p;
        }
    }

    public Node top;

    public boolean isEmpty() {
        return top == null;
    }

    public void push(Integer num) {
        top = new Node(num, top);
    }

    public Integer pop() {
        Integer ans = top.value;
        top = top.prev;
        return ans;
    }

    public Integer peek() {
        return top.value;
    }

    public static void main(String[] args) {
        try {
            BadIntegerStack test = new BadIntegerStack();
            test.pop();
        }
        catch(NullPointerException error){
            System.out.println("Success!");
        }
        try {
            BadIntegerStack test = new BadIntegerStack();
            test.peek();
        }
        catch(NullPointerException error){
            System.out.println("Success!");
        }

        BadIntegerStack infiniteStack = new BadIntegerStack();
        infiniteStack.push(1);
        infiniteStack.top.prev = infiniteStack.top;
        int i = 0;
        while(!infiniteStack.isEmpty() && i < 100){
            i++;
            infiniteStack.pop();
            System.out.println("Limit reached");
        }
    }
}