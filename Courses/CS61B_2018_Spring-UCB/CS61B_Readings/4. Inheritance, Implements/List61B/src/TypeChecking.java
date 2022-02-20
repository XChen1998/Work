public class TypeChecking {
    public static void main(String[] args) {

        VengefulSLList<Integer> vsl = new VengefulSLList<Integer>(9);
        SLList<Integer> sl = vsl;

        sl.addLast(50);
        sl.removeLast();
        sl.printLostItems();
        /*VengefulSLList<Integer>vsl2 = sl;*/
    }
}

