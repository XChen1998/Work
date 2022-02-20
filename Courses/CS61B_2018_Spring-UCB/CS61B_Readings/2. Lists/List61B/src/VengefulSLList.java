public class VengefulSLList<Item> extends SLList<Item> {
    public SLList<Item> deletedItems;

    public VengefulSLList() {
        super();
        deletedItems = new SLList<Item>();
    }

    public VengefulSLList(Item x) {
        super(x);
        deletedItems = new SLList<Item>();
    }

    /*The super keyword tells the compiler
    to use the removeLast() of the father class*/
    @Override
    public Item removeLast() {
        Item x = super.removeLast();
        deletedItems.addLast(x);
        return x;
    }

    public void printLostItems() {
        deletedItems.print();
    }


    public static void main(String[] args) {
        VengefulSLList<Integer> vs1 = new VengefulSLList();
        VengefulSLList<Integer> vs2 = new VengefulSLList(1);
        vs1.addLast(1);
        vs1.addLast(5);
        vs1.addLast(10);
        vs1.addLast(13);
        vs1.print();

        vs1.removeLast();
        vs1.removeLast();
        vs1.print();
        vs1.deletedItems.print();
    }
}
