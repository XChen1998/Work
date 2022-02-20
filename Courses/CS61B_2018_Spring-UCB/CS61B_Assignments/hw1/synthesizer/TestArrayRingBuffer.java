package synthesizer;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Tests the ArrayRingBuffer class.
 *
 * @author Josh Hug
 */

public class TestArrayRingBuffer {
    @Test
    public void someTest() {
        ArrayRingBuffer<Integer> arb = new ArrayRingBuffer<>(10);
        for (int i = 0; i < 10; i++) {
            arb.enqueue(i);
        }
        for (int i = 0; i < 3; i++) {
            arb.dequeue();
        }
        for (int i = 10; i < 13; i++) {
            arb.enqueue(i);
        }
        for (int i : arb) {
            System.out.println(i);
        }
        for (int i = 0; i < 3; i++) {
            arb.dequeue();
        }
        for (int i : arb) {
            System.out.println(i);
        }
    }

    /**
     * Calls tests for ArrayRingBuffer.
     */
    public static void main(String[] args) {
        jh61b.junit.textui.runClasses(TestArrayRingBuffer.class);
    }
} 
