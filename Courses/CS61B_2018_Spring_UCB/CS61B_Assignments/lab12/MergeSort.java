import edu.princeton.cs.algs4.Queue;

public class MergeSort {
    /**
     * Removes and returns the smallest item that is in q1 or q2.
     * <p>
     * The method assumes that both q1 and q2 are in sorted order, with the smallest item first. At
     * most one of q1 or q2 can be empty (but both cannot be empty).
     *
     * @param q1 A Queue in sorted order from least to greatest.
     * @param q2 A Queue in sorted order from least to greatest.
     * @return The smallest item that is in q1 or q2.
     */
    private static <Item extends Comparable> Item getMin(
            Queue<Item> q1, Queue<Item> q2) {
        if (q1.isEmpty()) {
            return q2.dequeue();
        } else if (q2.isEmpty()) {
            return q1.dequeue();
        } else {
            // Peek at the minimum item in each queue (which will be at the front, since the
            // queues are sorted) to determine which is smaller.
            Comparable q1Min = q1.peek();
            Comparable q2Min = q2.peek();
            if (q1Min.compareTo(q2Min) <= 0) {
                // Make sure to call dequeue, so that the minimum item gets removed.
                return q1.dequeue();
            } else {
                return q2.dequeue();
            }
        }
    }

    /**
     * Returns a queue of queues that each contain one item from items.
     */
    /*XChen: I really do not understand why there is such a redundant method.*/
    private static <Item extends Comparable> Queue<Queue<Item>>
        makeSingleItemQueues(Queue<Item> items) {
        Queue<Queue<Item>> returned = new Queue<>();
        for (Item item : items) {
            Queue<Item> cur = new Queue<>();
            cur.enqueue(item);
            returned.enqueue(cur);
        }
        return returned;
    }

    /**
     * Returns a new queue that contains the items in q1 and q2 in sorted order.
     * <p>
     * This method should take time linear in the total number of items in q1 and q2.  After
     * running this method, q1 and q2 will be empty, and all of their items will be in the
     * returned queue.
     *
     * @param q1 A Queue in sorted order from least to greatest.
     * @param q2 A Queue in sorted order from least to greatest.
     * @return A Queue containing all of the q1 and q2 in sorted order, from least to
     * greatest.
     */
    private static <Item extends Comparable> Queue<Item> mergeSortedQueues(
            Queue<Item> q1, Queue<Item> q2) {
        Queue<Item> returned = new Queue<>();
        while (!q1.isEmpty() || !q2.isEmpty()) {
            returned.enqueue(getMin(q1, q2));
        }
        return returned;
    }

    /**
     * Returns a Queue that contains the given items sorted from least to greatest.
     */
    public static <Item extends Comparable> Queue<Item> mergeSort(
            Queue<Item> items) {
        if (items.size() <= 1) {
            return items;
        }
        int midPoint = items.size() / 2;
        Queue<Item> q1 = new Queue<>();
        Queue<Item> q2 = new Queue<>();
        for (int i = 0; i < midPoint; i++) {
            q1.enqueue(items.dequeue());
        }
        while (!items.isEmpty()) {
            q2.enqueue(items.dequeue());
        }
        q1 = mergeSort(q1);
        q2 = mergeSort(q2);
        return (mergeSortedQueues(q1, q2));
    }

    public static void main(String[] args) {
        Queue<String> students = new Queue<String>();
        students.enqueue("Rose");
        students.enqueue("Cathy");
        students.enqueue("Alice");
        students.enqueue("Ethan");
        students.enqueue("Vanessa");
        students.enqueue("Bob");
        students.enqueue("Edouard");
        students.enqueue("Tony");
        System.out.println(students);
        System.out.println(MergeSort.mergeSort(students));
    }
}
