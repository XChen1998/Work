public class SLList<T> {

    StuffNode sentinel;
    int size;

    private class StuffNode {
        T item;
        StuffNode next;

        public StuffNode() {
        }

        public StuffNode(T x, StuffNode s) {
            item = x;
            next = s;
        }
    }

    public SLList() {
        sentinel = new StuffNode();
        size = 0;
    }

    public void insertFront(T x) {
        sentinel.next = new StuffNode(x, sentinel.next);
        size++;
    }

    /* Returns the index of x in the list, if it exists.
   Otherwise, returns -1 */
    public int indexOf(T x) {
        StuffNode p = sentinel.next;
        int Index = 0;
        while (p != null) {
            if (x == p.item) {
                return Index;
            }
            p = p.next;
            Index++;
        }
        return -1;
    }
}
