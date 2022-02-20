public class IntList {
    public int first;
    public IntList rest;

    /**
     * Return the size of the list using... recursion!
     */
    public int size() {
        if (this.rest == null) {
            return 1;
        }
        return 1 + this.rest.size();
    }

    public IntList(int f, IntList r) {
        first = f;
        rest = r;
    }



    public int get(int i) {
        int element = 0;
        IntList p = this;
        while (element < i) {
            p = p.rest;
            element++;
        }
        return p.first;
    }

    public int getR(int i) {
        if (i == 0) {
            return this.first;
        }
        return this.rest.getR(i - 1);
    }

    public void addFirst(int i){
        rest = new IntList(first, rest);
        first = i;
    }

    public static void main(String[] args) {
        IntList L = new IntList(5, null);
        L.rest = new IntList(10, null);
        L.rest.rest = new IntList(15, null);
        L.addFirst(20);
        L.addFirst(25);
        System.out.println(L.size());
        System.out.println(L.get(0));
        System.out.println(L.get(1));
        System.out.println(L.get(2));

        System.out.println(L.getR(0));
        System.out.println(L.getR(1));
        System.out.println(L.getR(2));
    }
}