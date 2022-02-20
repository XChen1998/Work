public class SLList<Item> implements List61B<Item> {
    private class IntNode {
        public Item item;
        public IntNode next;

        public IntNode(Item item, IntNode next) {
            this.item = item;
            this.next = next;
        }
    }


    private IntNode first;
    private int size;

    public SLList(){}

    public SLList(Item x)
    {
        first = new IntNode(x, null);
        size = 1;

    }

    @Override
    public Item getFirst() {
        return first.item;
    }

    @Override
    public Item getLast() {
        return getNode(size - 1).item;
    }

    @Override
    public Item removeLast() {
        IntNode secondtoLast = getNode(size - 2);
        Item x = secondtoLast.next.item;
        secondtoLast.next = null;
        size--;
        return x;
    }

    @Override
    public Item get(int i) {
        Item x = getNode(i).item;
        return x;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void addFirst(Item x) {
        first = new IntNode(x, first);
        size++;
    }

    //    private void insert(int item, int position, IntNode p){
//        IntNode x = p;
//
//    }

    @Override
    public void addLast(Item item) {
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

    @Override
    public void insert(Item item, int position) {
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

    public void insertSolution(Item item, int position) {
        if (first == null || position == 0) {
            addFirst(item);
            return;
        }
        IntNode currentNode = first;
        while (position > 1 && currentNode.next != null) {
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

    @Override
    public void print() {
        /*System.out.println("Private Print().");*/
        for (IntNode p = first; p != null; p = p.next) {
            System.out.print(p.item + " ");
        }
        System.out.println();
    }

    public void printLostItems(){

    }

/*    public static void main(String[] args) {
        SLList<Integer> a = new SLList();
        a.addFirst(1);
        a.addLast(2);
        a.removeLast();
        a.addFirst(3);
        a.removeLast();
    }*/
}