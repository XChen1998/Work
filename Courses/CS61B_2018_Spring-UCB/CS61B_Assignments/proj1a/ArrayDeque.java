public class ArrayDeque<T> {
    private int size;
    private int nextFirst;
    private int nextLast;
    private T[] items;

    public ArrayDeque() {
        size = 0;
        nextFirst = 4;
        nextLast = 5;
        items = (T[]) new Object[8];
    }

/*    public ArrayDeque(ArrayDeque other) {
        this.size = other.size;
        this.bSize = other.bSize;
        for (int i = 0; i < other.items.length; i++) {
            this.items[i] = (T) other.items[i];
        }
    }*/

    private void expandSize() {
        T[] expandedArray = (T[]) new Object[items.length * 2];
        for (int i = 0; i < size; i++) {
            expandedArray[i] = items[realIndex2itemIndex(i)];
        }
        items = expandedArray;
        nextFirst = expandedArray.length - 1;
        nextLast = size;
    }

/*         XC: A rather ugly method.
        if (nextLast == 0 && nextFirst == items.length - 1) {
            System.arraycopy(items, nextLast, expandedArray,
                    expandedArray.length - items.length + nextLast,
                    items.length - nextLast);
        } else {
            System.arraycopy(items, nextLast, expandedArray,
                    expandedArray.length - items.length + nextLast,
                    items.length - nextLast);
            System.arraycopy(items, 0, expandedArray,
                    0, nextFirst + 1);
        }
        nextFirst = expandedArray.length - (items.length - nextLast) - 1;
        items = expandedArray;*/

    private void compressSize() {
        T[] compressedArray = (T[]) new Object[items.length / 2];
        for (int i = 0; i < size; i++) {
            compressedArray[i] = items[realIndex2itemIndex(i)];
        }
        items = compressedArray;
        nextFirst = compressedArray.length - 1;
        nextLast = size;
    }

    private int addReturnNextFirst() {
        if (nextFirst == 0) {
            return items.length - 1;
        } else {
            return nextFirst - 1;
        }
    }

    private int removeReturnNextFirst() {
        if (nextFirst == items.length - 1) {
            return 0;
        } else {
            return nextFirst + 1;
        }
    }

    private int addReturnNextLast() {
        if (nextLast == items.length - 1) {
            return 0;
        } else {
            return nextLast + 1;
        }
    }

    private int removeReturnNextLast() {
        if (nextLast == 0) {
            return items.length - 1;
        } else {
            return nextLast - 1;
        }
    }

    public void addFirst(T item) {
        if (size == items.length) {
            expandSize();
        }
        items[nextFirst] = item;
        nextFirst = addReturnNextFirst();
        size += 1;
        if (size < items.length / 4) {
            compressSize();
        }
    }

    public void addLast(T item) {
        if (size == items.length) {
            expandSize();
        }
        items[nextLast] = item;
        nextLast = addReturnNextLast();
        size++;
        if (size < items.length / 4) {
            compressSize();
        }
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    private int realIndex2itemIndex(int x) {
        return (x + nextFirst + 1) % items.length;
    }

    public void printDeque() {
        for (int i = 0; i < size; i++) {
            System.out.print(items[realIndex2itemIndex(i)]);
            System.out.print(" ");
        }
        System.out.println();
    }

    public T removeFirst() {
        if (this.isEmpty()) {
            return null;
        }
        T x = this.get(0);
        //this.items[realIndex2itemIndex(0)] = null;
        nextFirst = removeReturnNextFirst();
        size--;
        if (size < items.length / 4) {
            compressSize();
        }
        return x;
    }

    public T removeLast() {
        if (this.isEmpty()) {
            return null;
        }
        T x = this.get(size - 1);
        //this.items[realIndex2itemIndex(size - 1)] = null;
        nextLast = removeReturnNextLast();
        size--;
        if (size < items.length / 4) {
            compressSize();
        }
        return x;
    }

    public T get(int index) {
        return items[realIndex2itemIndex(index)];
    }
}
