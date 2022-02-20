import org.junit.Test;

public class ArrayDequeTest {
    public static void testRandomAdd() {
        System.out.println("------------------------------.");
        System.out.println("Test random add.");
        ArrayDeque<Integer> p = new ArrayDeque();
        for (int i = 0; i < 15; i++) {
            double random = Math.random();
            if (0 < random && random < 0.5) {
                p.addFirst(i);
                System.out.print("Add first ");
                System.out.print(p.get(0));
                System.out.print(" ");
                System.out.println(i);
                p.printDeque();
            }
            if (0.5 < random && random < 1) {
                p.addLast(i);
                System.out.print("Add last ");
                System.out.print(p.get(i));
                System.out.print(" ");
                System.out.println(i);
                p.printDeque();
            }
        }
        System.out.println("Test random add finished.");
        System.out.println("------------------------------.");
    }

    public static void testaddremoveis() {
        System.out.println("------------------------------.");
        System.out.println("Test random add and random remove.");
        ArrayDeque<Integer> p = new ArrayDeque();
        int trueSize = 0;
        for (int i = 0; i < 30; i++) {
            double random = Math.random();
            if (0 < random && random < 0.25) {
                trueSize++;
                p.addFirst(i);
                System.out.println("Add first.");
                p.printDeque();
            }
            if (0.25 < random && random < 0.5) {
                trueSize++;
                p.addLast(i);
                System.out.println("Add last.");
                p.printDeque();
            }
            if (0.5 < random && random < 0.75) {
                p.removeFirst();
                if (trueSize != 0) {
                    trueSize--;
                }
                System.out.println("Remove First.");
                p.printDeque();
            }
            if (0.75 < random && random < 1) {
                p.removeLast();
                if (trueSize != 0) {
                    trueSize--;
                }
                System.out.println("Remove Last.");
                p.printDeque();
            }
        }
        System.out.println("Test random add and random remove finished.");
        System.out.println("------------------------------.");
    }

    public static void fillRemove() {
        System.out.println("------------------------------.");
        System.out.println("Test fill remove.");
        ArrayDeque<Integer> p = new ArrayDeque();
        for (int i = 0; i < 15; i++) {
            double random = Math.random();
            if (0 < random && random < 0.5) {
                p.addFirst(i);
                System.out.print("Add first ");
                System.out.print(p.get(0));
                System.out.print(" ");
                System.out.println(i);
                p.printDeque();
            }
            if (0.5 < random && random < 1) {
                p.addLast(i);
                System.out.print("Add last ");
                System.out.print(p.get(i));
                System.out.print(" ");
                System.out.println(i);
                p.printDeque();
            }
        }

        for (int i = 0; i < 15; i++) {
            double random = Math.random();
            if (0 < random && random < 0.5) {

                System.out.print("Remove first ");
                System.out.println(p.removeFirst());
                p.printDeque();
            }
            if (0.5 < random && random < 1) {
                System.out.print("Remove last ");
                System.out.println(p.removeLast());
                p.printDeque();
            }
        }
        System.out.println("Test fill remove finished.");
        System.out.println("------------------------------.");
    }

    @Test
    public void test1(){
        testRandomAdd();
    }

    @Test
    public void test2(){
        testaddremoveis();
    }
    @Test

    public void test3(){
        fillRemove();
    }
}
