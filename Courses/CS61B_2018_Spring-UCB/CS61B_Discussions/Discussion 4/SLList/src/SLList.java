public class SLList {
    private static class IntNode {
        public int item;
        public IntNode next;

        public IntNode(int item, IntNode next) {
            this.item = item;
            this.next = next;
        }
    }

    private IntNode first;
    private int size;

    public void addFirst(int x) {
        first = new IntNode(x, first);
        size++;
    }

    //    private void insert(int item, int position, IntNode p){
//        IntNode x = p;
//
//    }
    public void addLast(int item) {
        if (first == null) {
            size++;
            first = new IntNode(item, null);
            return;

        }

        IntNode p = first;
        while (p.next != null) {
            p = p.next;
        }
        p.next = new IntNode(item, null);
        size++;
    }

    public void insert(int item, int position) {
        if (position == 0) {
            addFirst(item);
            return;
        }
        if (position >= size) {
            addLast(item);
            return;
        }
        IntNode added = new IntNode(item, getNode(position));
        getNode(position - 1).next = added;
        size++;
    }

    public void insertSolution(int item, int position) {
        if (first == null || position == 0) {
            addFirst(item);
            return;
        }
        IntNode currentNode = first;
        while (position > 1 && currentNode.next != null){
            position--;
            currentNode = currentNode.next;
        }
        IntNode newNode = new IntNode(item, currentNode.next);
        currentNode.next = newNode;
    }

    public void reverse() {
        if (first == null || first.next == null) {
            return;
        }
        IntNode last = getNode(size - 1);
        for (int i = size - 1; i > 0; i--) {
            getNode(i).next = getNode(i - 1);
        }
        first = last;
        getNode(size - 1).next = null;
    }

    public void reverseSolution() {
        IntNode rBack = null;
        IntNode rFront = first;
        while (rFront != null) {
            IntNode nextrFront = rFront.next;
            rFront.next = rBack;
            rBack = rFront;
            rFront = nextrFront;
        }
        first = rBack;
    }

    public void reverseR() {
        first = reverseR(first);
    }

    private IntNode reverseR(IntNode p) {
        if (p.next == null || p == null) {
            return p;
        } else {
            IntNode reversed = reverseR(p.next);
            p.next.next = p;
            p.next = null;
            return reversed;

        }
    }

    private IntNode getNode(int index) {
        IntNode p = first;
        for (int i = 0; i < index; i++) {
            p = p.next;
        }
        return p;
    }

    public static SLList testInsert() {
        SLList a = new SLList();
        for (int i = 0; i < 3; i++) {
            a.addLast(i);
        }
        for (int i = 0; i < 3; i++) {
            a.insert(-1, 4);
        }
        return a;
    }

    public static SLList testReverse() {
        SLList a = new SLList();
        for (int i = 0; i < 15; i++) {
            a.addLast(i);
        }
        a.reverse();
        return a;
    }

    public static SLList testReverseSolution() {
        SLList a = new SLList();
        for (int i = 0; i < 15; i++) {
            a.addLast(i);
        }
        a.reverseSolution();
        return a;
    }

    public static SLList testReverseR() {
        SLList a = new SLList();
        for (int i = 0; i < 5; i++) {
            a.addLast(i);
        }
        a.reverseR();
        return a;
    }

    public static SLList testReverseFast() {
        SLList a = new SLList();
        for (int i = 0; i < 5; i++) {
            a.addLast(i);
        }
        a.reverseSolution();
        return a;
    }

    public static void main(String[] args) {
        //LList a = testInsert();
        //SLList b = testReverse();
        //SLList c = testReverseR();
        //SLList d = testReverseFast();
        SLList e = testReverseSolution();
    }
}