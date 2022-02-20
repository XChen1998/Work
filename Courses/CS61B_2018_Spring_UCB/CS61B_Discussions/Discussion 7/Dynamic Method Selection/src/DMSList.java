public class DMSList {
    private IntNode sentinel;

    public DMSList() {
        sentinel = new IntNode(-1000, new LastIntNode());
    }

    public void addFirst(int x){
        sentinel.next = new IntNode(x, sentinel.next);
    }

    public class IntNode {
        public int item;
        public IntNode next;

        public IntNode(int i, IntNode h) {
            item = i;
            next = h;
        }

        public int max() {
            return Math.max(item, next.max());
        }
    }

    public class LastIntNode extends IntNode {
        public LastIntNode() {
            super(0, null);
        }

        @Override
        public int max() {
            return 0;
        }

    }

    public int max() {
        return sentinel.next.max();
    }

    public static void main(String[] args) {
        DMSList test = new DMSList();
        for (int i = 0; i < 10; i++){
            test.addFirst(10 - i + (int)(Math.random()*15));
        }
        System.out.println(test.max());
    }
}
