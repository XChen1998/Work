package GoodIntegerStack;

public class GoodIntegerStack {
    private class Node {
        private Integer value;
        private Node prev;

        private Node(Integer v, Node p) {
            value = v;
            prev = p;
        }
    }

    private Node top;

    public boolean isEmpty() {
        return top == null;
    }

    public void push(Integer num) {
        top = new Node(num, top);
    }

    public Integer pop() {
        if (this.isEmpty()) {
            return null;
        }
        Integer ans = top.value;
        top = top.prev;
        return ans;
    }

    public Integer peek() {
        if (this.isEmpty()) {
            return null;
        }
        return top.value;
    }
}