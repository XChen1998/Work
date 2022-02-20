package Iterator;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class KthIntList implements Iterator<Integer> {
    public int k;
    private IntList curList;
    private boolean hasNext;

    public KthIntList(IntList I, int k) {
        this.k = k;
        this.curList = I;
        this.hasNext = true;
    }

    public boolean hasNext() {
        return this.hasNext;
    }

    public Integer next() {
        if (curList == null) {
            this.hasNext = false;
            throw new NoSuchElementException("No such element!");
        }
        int valueToReturn = curList.first;
        for (int i = 0; i < this.k && curList != null; i++) {
            curList = curList.rest;
        }
        return valueToReturn;
    }

    public static void main(String[] args) {
        IntList test = IntList.of(1, 2, 3, 4, 5, 6, 7, 8, 9);
        for (Iterator<Integer> p = new KthIntList(test, 3); p.hasNext(); ) {
            System.out.println(p.next());
        }
    }
}