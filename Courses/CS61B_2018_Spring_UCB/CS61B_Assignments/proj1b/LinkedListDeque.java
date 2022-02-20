public class LinkedListDeque<Item> implements Deque<Item> {
    private StuffNode frontSentinel;
    private StuffNode backSentinel;
    private int size;


    private class StuffNode {
        private Item item;
        private StuffNode previous;
        private StuffNode next;

        public StuffNode() {
        }

        public StuffNode(Item i, StuffNode n, StuffNode p) {
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
    @Override
    public void addFirst(Item item) {
        StuffNode p = new StuffNode(item, frontSentinel.next, frontSentinel);
        frontSentinel.next.previous = p;
        frontSentinel.next = p;
        size += 1;
    }

    @Override
    public void addLast(Item item) {
        StuffNode p = new StuffNode(item, backSentinel, backSentinel.previous);
        backSentinel.previous.next = p;
        backSentinel.previous = p;
        size += 1;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
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

    @Override
    public void printDeque() {
        printDeque(frontSentinel.next);
        System.out.println();
    }

    @Override
    public Item removeFirst() {
        if (this.isEmpty()) {
            return null;
        } else {
            Item value2return = frontSentinel.next.item;
            frontSentinel.next = frontSentinel.next.next;
            frontSentinel.next.previous = frontSentinel;
            size -= 1;
            return value2return;
        }
    }

    @Override
    public Item removeLast() {
        if (this.isEmpty()) {
            return null;
        } else {
            Item value2return = backSentinel.previous.item;
            backSentinel.previous = backSentinel.previous.previous;
            backSentinel.previous.next = backSentinel;
            size -= 1;
            return value2return;
        }
    }

    private Item get(int index, StuffNode p) {
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

    @Override
    public Item get(int index) {
        return get(index, this.frontSentinel.next);
    }

    private Item getRecursive(int index, StuffNode p) {
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

    public Item getRecursive(int index) {
        return get(index, this.frontSentinel.next);
    }
}
