public class LinkedListDeque<T> {
    private StuffNode frontSentinel;
    private StuffNode backSentinel;
    private int size;


    private class StuffNode {
        private T item;
        private StuffNode previous;
        private StuffNode next;

        public StuffNode() {
        }

        public StuffNode(T i, StuffNode n, StuffNode p) {
            item = i;
            previous = p;
            next = n;
        }
    }

    public LinkedListDeque() {
        frontSentinel = new StuffNode();
        backSentinel = new StuffNode();
        frontSentinel.next = backSentinel;
        backSentinel.previous = frontSentinel;
        size = 0;
    }

//    public LinkedListDeque(LinkedListDeque other) {
//        LinkedListDeque p = new LinkedListDeque();
//        for (int i = 0; i < other.size(); i++) {
//            p.addLast(other.get(i));
//        }
//        this.size = p.size;
//        this.frontSentinel = p.frontSentinel;
//        this.backSentinel = p.backSentinel;
//    }

    public void addFirst(T item) {
        StuffNode p = new StuffNode(item, frontSentinel.next, frontSentinel);
        frontSentinel.next.previous = p;
        frontSentinel.next = p;
        size += 1;
    }

    public void addLast(T item) {
        StuffNode p = new StuffNode(item, backSentinel, backSentinel.previous);
        backSentinel.previous.next = p;
        backSentinel.previous = p;
        size += 1;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    private void printDeque(StuffNode p) {
        if (p.previous == null || p.next == null) {
            return;
        } else {
            System.out.print(p.item);
            System.out.print(' ');
        }
        printDeque(p.next);

    }

    public void printDeque() {
        printDeque(frontSentinel.next);
        System.out.println();
    }

    public T removeFirst() {
        if (this.isEmpty()) {
            return null;
        } else {
            T value2return = frontSentinel.next.item;
            frontSentinel.next = frontSentinel.next.next;
            frontSentinel.next.previous = frontSentinel;
            size -= 1;
            return value2return;
        }
    }

    public T removeLast() {
        if (this.isEmpty()) {
            return null;
        } else {
            T value2return = backSentinel.previous.item;
            backSentinel.previous = backSentinel.previous.previous;
            backSentinel.previous.next = backSentinel;
            size -= 1;
            return value2return;
        }
    }

    private T get(int index, StuffNode p) {
        if (index >= size || index < 0) {
            return null;
        } else {
            int pos = 0;
            while (p.next.item != null && pos < index) {
                p = p.next;
                pos++;
            }
        }
        return p.item;
    }

    public T get(int index) {
        return get(index, this.frontSentinel.next);
    }

    private T getRecursive(int index, StuffNode p) {
        if (index >= size || index < 0) {
            return null;
        } else {
            if (index == 0) {
                return p.item;
            } else {
                return getRecursive(index - 1, p.next);
            }
        }
    }

    public T getRecursive(int index) {
        return get(index, this.frontSentinel.next);
    }
}
