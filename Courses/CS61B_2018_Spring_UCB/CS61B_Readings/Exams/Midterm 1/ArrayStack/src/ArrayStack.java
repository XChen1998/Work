public class ArrayStack<Item> implements Stack<Item> {
    private Item[] items;
    private int size;

    public ArrayStack() { // initial array size is 8 items = (Item[]) new Object[8];
        items = (Item[]) new Object[8];
        size = 0;
    }

    private void resize(int capacity) { // resizes array to given capacity
        Item[] newItems = (Item[]) new Object[capacity * 2];
        System.arraycopy(items, 0, newItems, 0, size);
        items = newItems;
    }

    public void push(Item x) {
        if (usageRatio() == 1) {
            resize(items.length * 2);
        }
        items[size] = x;
        size++;
    }

    public Item pop() { // returns null if stack is empty
        if (size == 0) {
            return null;
        }
        if (usageRatio() < 0.25 && items.length > 8) {
            resize(items.length / 2);
        }
        Item x = items[size - 1];
        items[size - 1] = null;
        size--;
        return x;

    }

    public int size() {
        return size;
    }

    private double usageRatio() {
        return ((double) size()) / items.length;
    }
}