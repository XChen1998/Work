public class SLList {
    private IntNode first;

    public SLList(int x) {
        first = new IntNode(x, null);
    }

    /* Adds x to the front of the list */
    public void addFirst(int x) {
        first = new IntNode(x, first);
    }

    /* get the first element in the list */
    public int getFirst() {
        return first.item;
    }

    public void addLast(int x) {
        IntNode p = first;
        while (p.next != null) {
            p = p.next;
        }
        p.next = new IntNode(x, null);
    }

    public static void main(String[] args) {
        /* Creates a list of one integer, namely 10*/

        SLList L = new SLList(15);
        L.addFirst(10);
        L.addFirst(5);
        L.addLast((20));
        System.out.println(L.getFirst());


        IntNode LL = new IntNode(15, null);
        LL.addFirst(10);
        LL.addFirst(5);

        System.out.println(LL.getFirst());
    }
}