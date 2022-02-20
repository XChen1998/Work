import java.lang.reflect.Array;
import java.util.Iterator;

public class ArraySet<T> implements Iterable<T> {

    private T[] items;
    private int size;

    public ArraySet() {
        items = (T[]) new Object[100];
        size = 0;
    }

    public Iterator<T> iterator() {
        return new ArraySetIterator();
    }

    private class ArraySetIterator implements Iterator<T> {
        private int wizPos;

        public ArraySetIterator() {
            wizPos = 0;
        }

        public boolean hasNext() {
            return (wizPos < size);
        }

        public T next() {
            T returnItem = items[wizPos];
            wizPos++;
            return returnItem;
        }
    }


    @Override
    public String toString() {
        String aset = "(";
        for (int i = 0; i < size - 1; i++) {
            aset += items[i].toString();
            aset += ", ";
        }
        aset += items[size - 1];
        aset += ")";
        return aset;
    }


    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }

        if (other == null) {
            return false;
        }

        if (this.getClass() != other.getClass()) {
            return false;
        }

        ArraySet<T> o = (ArraySet<T>) other;

        for (T i : this) {
            if (o.contains(i)) {
                return false;
            }
        }
        return true;
    }

    /* Returns true if this map contains a mapping for the specified key.
     */
    public boolean contains(T x) {
        for (int i = 0; i < size; i++) {
            if (items[i] == null) {
                System.out.println("null item detected, you should not add any null value to the array set!");
                continue; // Skip any null in the ArraySet
            }
            if (items[i].equals(x)) {
                return true;
            }
        }
        return false;
    }

    private void expand() {
        T[] newItems = (T[]) new Object[size * 2];
        System.arraycopy(items, 0, newItems, 0, size);
        items = newItems;
    }

    /* Associates the specified value with the specified key in this map.
       Throws an IllegalArgumentException if the key is null. */
    public void add(T x) {
        if (!this.contains(x)) {
            if (size == items.length) {
                expand();
            }
            items[size] = x;
            size++;
        }
    }

    /* Returns the number of key-value mappings in this map. */
    public int size() {
        return size;
    }

    public static void main(String[] args) {
        ArraySet<Integer> aset = new ArraySet<>();
        aset.add(5);
        aset.add(23);
        aset.add(42);

        //iteration
        for (int i : aset) {
            System.out.println(i);
        }

        //toString
        System.out.println(aset);
        ArraySet<Integer> aset2 = new ArraySet<>();
        aset2.add(5);
        aset2.add(23);
        aset2.add(42);

        System.out.println(aset.equals(aset2));
        System.out.println(aset.equals(null));
        System.out.println(aset.equals("fish"));
        System.out.println(aset.equals(aset));

        //equals
    }
    /* Also to do:
    1. Make ArraySet implement the Iterable<T> interface.
    2. Implement a toString method.
    3. Implement an equals() method.
    */
}