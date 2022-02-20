import static org.junit.Assert.*;

import org.junit.Test;

public class TestArrayDequeGold {
    @Test
    public void testRandomCall() {
        String test = "";
        ArrayDequeSolution<Integer> solution = new ArrayDequeSolution();
        StudentArrayDeque<Integer> student = new StudentArrayDeque<>();
        Integer expected = 0;
        Integer actual = 0;
        for (int i = 0; i < 200; i++) {
            /*System.out.println("Test: " + (1+i));*/
            double randomNumber = StdRandom.uniform();
            if (randomNumber >= 0 && randomNumber <= 0.25) {
                solution.addFirst(i);
                student.addFirst(i);
                expected = solution.get(0);
                actual = student.get(0);
                test += "addFirst(" + i + ")" + "\n";
            } else {
                if (randomNumber > 0.25 && randomNumber <= 0.5) {
                    solution.addLast(i);
                    student.addLast(i);
                    expected = solution.get(0);
                    actual = student.get(0);
                    test += "addLast(" + i + ")" + "\n";
                } else {
                    if (solution.size() > 0 && student.size() > 0) {
                        if (randomNumber > 0.5 && randomNumber <= 0.75) {
                            expected = solution.removeFirst();
                            actual = student.removeFirst();
                            test += "removeFirst()\n";
                        } else {
                            if (randomNumber > 0.75 && randomNumber <= 1) {
                                expected = solution.removeLast();
                                actual = student.removeLast();
                                test += "removeLast()\n";
                            }
                        }
                    }
                }
            }
            assertEquals(test, expected, actual);
        }


    }
}
