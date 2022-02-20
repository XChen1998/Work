public class DLListOfInts implements ListOfInts {
    public class IntNode {
        public int item;
        public IntNode next, prev;

        public IntNode(int i, IntNode n, IntNode p) {
            item = i;
            next = n;
            prev = p;
        }
    }

    public IntNode sentinel;
    public int size;

    public DLListOfInts() {
        sentinel = new IntNode(0, sentinel, sentinel);
        size = 0;
    }

    public void plusEquals(DLListOfInts x) {
        if (this.size() != x.size() || x.size == 0) {
            return;
        }
        IntNode X = x.sentinel.next;
        for (IntNode p = sentinel.next; p != sentinel; p = p.next) {
            p.item += X.item;
            X = X.next;
        }
    }

    private void addLast(int i, IntNode x) {
        if (size == 0) {
            x.next = new IntNode(i, sentinel, sentinel);
        } else {
            while (x.next != sentinel) {
                x = x.next;
            }
            x.next = new IntNode(i, sentinel, x);
            sentinel.prev = x.next;
        }
    }

    public void addLast(int i) {
        addLast(i, sentinel);
        size++;
    }

    ;

    private int get(int i, IntNode p) {
        ;
        if (i == 0) {
            return p.item;
        } else {
            return get(i - 1, p.next);
        }
    }

    public int get(int i) {
        return get(i, sentinel.next);
    }

    public int size() {
        return size;
    }

    ;

    public void set(int i, int value) {
        IntNode p = sentinel.next;
        for (int j = 0; j < i; j++) {
            p = p.next;
        }
        p.item = value;
    }

    ;

}