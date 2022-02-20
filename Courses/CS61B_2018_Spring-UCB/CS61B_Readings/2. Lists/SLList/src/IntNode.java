public class IntNode {
    public int item;
    public IntNode next;

    public IntNode(int i, IntNode n) {
        item = i;
        next = n;
    }

    public void addFirst(int x){
        this.next = new IntNode(item, this.next);
        this.item = x;
    }

    public int getFirst(){
        return this.item;
    }
}