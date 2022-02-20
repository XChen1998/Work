public class RotatingSLList<Item> extends SLList<Item> {

    /*Rotate SLList*/
    public void rotateRight() {
        this.addFirst(this.removeLast());
    }

    public static void main(String[] args) {
        RotatingSLList<Integer> rsl = new RotatingSLList();
        rsl.addLast(10);
        rsl.addLast(11);
        rsl.addLast(12);
        rsl.addLast(13);
        rsl.print();
        rsl.rotateRight();
        rsl.print();
    }
}
