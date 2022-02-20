package Iterable;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class KthIntList implements Iterable<Integer> {
    public int k;
    private IntList curList;
    private boolean hasNext;

    public KthIntList(IntList I, int k) {
        this.k = k;
        this.curList = I;
        this.hasNext = true;
    }

    public Iterator iterator() {
        return new KthIntListIterator();
    }

    private class KthIntListIterator implements Iterator<Integer>{
        public KthIntListIterator(){

        }

        public boolean hasNext() {
            return hasNext;
        }

        public Integer next() {
            if (curList == null) {
                hasNext = false;
                throw new NoSuchElementException("No such element!");
            }
            int valueToReturn = curList.first;
            for (int i = 0; i < k && curList != null; i++) {
                curList = curList.rest;
            }
            return valueToReturn;
        }
    }


    public static void main(String[] args) {
        KthIntList test = new KthIntList(IntList.of(1, 2, 3, 4, 5, 6, 7, 8, 9), 2);
        for (int p :  test) {
            System.out.println(p);
        }
    }
}