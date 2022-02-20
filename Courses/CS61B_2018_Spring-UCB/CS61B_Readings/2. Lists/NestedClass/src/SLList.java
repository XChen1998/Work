public class SLList {
    private IntNode sentinel;
    private int size;
    private IntNode last;

    public static class IntNode { //Declare this class as static as it does not use any instance method or variables.
        public int item;
        public IntNode next;

        public IntNode(int i, IntNode n) {
            item = i;
            next = n;
        }
    }

    public SLList() {
        sentinel = new IntNode(63, null);
        size = 0;
        last = null;
    }

    public SLList(int x) {
        sentinel = new IntNode(63, null);
        sentinel.next = new IntNode(x, null);
        size = 1;
        last = sentinel.next;
    }

    /* Adds x to the front of the list */
    public void addFirst(int x) {
        sentinel.next = new IntNode(x, sentinel.next);
        size += 1;
    }

    /* get the first element in the list */
    public int getFirst() {
        return sentinel.next.item;
    }

    public void addLast(int x) {
        last.next = new IntNode(x, null);
        last = last.next;
        size += 1;
    }

//    public void addLastR(int x){
//        if (first == null){
//            first = new IntNode(x, null);
//        }
//        else{
//            SLList rest = first.next;
//            addLastR(x);
//        }
//    }

    public int size() {
        IntNode p = sentinel.next;
        int listSize = 0;
        while (p != null) {
            p = p.next;
            listSize++;
        }
        return listSize;
    }

    public int sizeFast() { // This method is based on an extra variable that record the size of a SLList.
        return size;
    }

    private static int sizeR(IntNode p) {
        if (p == null) {
            return 0;
        } else {
            return sizeR(p.next) + 1;
        }
    }

    private int get(IntNode p, int index){
        if (index == 0){
            return p.item;
        } else {
            return get(p.next, index - 1);
        }
    }

    public int get(int index){
            return get(sentinel.next, index);
    }

    public int sizeR() {
        return sizeR(sentinel.next);
    }

    public String longestString(){
        if (size == 0){
            return null;
        }
        String longest = "";
        for (int i = 0; i < size; i++){

        }
        return null;
    }

    public static void main(String[] args) {
        /* Creates a list of one integer, namely 10*/

/*        SLList L = new SLList(15);
        L.addFirst(10);
        L.addFirst(5);
        System.out.println((L.size()));
        System.out.println((L.sizeR()));
        System.out.println((L.sizeFast()));
        L.addLast((20));
        System.out.println(L.getFirst());
        System.out.println((L.size()));
        System.out.println((L.sizeR()));
        System.out.println((L.sizeFast()));
        System.out.println(L.getFirst());
        System.out.println(L.get(0));
        System.out.println(L.get(1));*/

        /* If we introduce a last, we also need a sentinel to avoid different data structure! */
//        SLList emptyL = new SLList();
//        emptyL.addLast(5);
//        System.out.println(emptyL.getFirst());
    }
}