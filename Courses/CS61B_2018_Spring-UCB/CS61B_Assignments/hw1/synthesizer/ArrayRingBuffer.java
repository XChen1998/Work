package synthesizer;

import java.util.Iterator;

public class ArrayRingBuffer<T> extends AbstractBoundedQueue<T> {
    /* Index for the next dequeue or peek. */
    private int first;            // index for the next dequeue or peek
    /* Index for the next enqueue. */
    private int last;
    /* Array for storing the buffer data. */
    private T[] rb;

    @Override
    public Iterator<T> iterator() {
        return new BufferIterator();
    }

    private class BufferIterator implements Iterator<T> {

        private int wizPos;
        private int number;

        private BufferIterator() {
            wizPos = first;
            number = 0;
        }

        private void changeWiz() {
            if (wizPos == capacity - 1) {
                wizPos = 0;
            } else {
                wizPos++;
            }
        }

        @Override
        public boolean hasNext() {
            number++;
            return !(wizPos == last) || (isFull() && number <= capacity);
        }

        @Override
        public T next() {
            T x = rb[wizPos];
            changeWiz();
            return x;
        }
    }

    /**
     * Create a new ArrayRingBuffer with the given capacity.
     */
    public ArrayRingBuffer(int capacity) {
        rb = (T[]) new Object[capacity];
        this.capacity = capacity;
        first = 0;
        last = 0;
        fillCount = 0;

    }

    /**
     * Adds x to the end of the ring buffer. If there is no room, then
     * throw new RuntimeException("Ring buffer overflow"). Exceptions
     * covered Monday.
     */
/*   private int real2index(int real) {
        return (real + first) % capacity;
    }*/
    @Override
    public void enqueue(T x) {
        if (isFull()) {
            throw new RuntimeException("Ring Buffer Overflow!");
        } else {
            rb[last] = x;
            last = (last + 1) % capacity;
            fillCount++;
        }
    }

    /**
     * Dequeue oldest item in the ring buffer. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow"). Exceptions
     * covered Monday.
     */
    public T dequeue() {
        if (isEmpty()) {
            throw new RuntimeException("Ring Buffer Underflow!");
        } else {
            T x = rb[first];
            first = (first + 1) % capacity;
            fillCount--;
            return x;
        }
    }

    /**
     * Return oldest item, but don't remove it.
     */
    public T peek() {
        if (isEmpty()) {
            throw new RuntimeException("Ring Buffer Overflow!");
        } else {
            return rb[first];
        }
    }

}



