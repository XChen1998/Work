import org.junit.Test;

public class testRemoveDuplicates {

    @Test
    public void tetsRemoveDuplicatesFunc() {
        IntList test = new IntList(3, null);
        test = new IntList(3, test);
        test = new IntList(3, test);
        test = new IntList(2, test);
        test = new IntList(2, test);
        test = new IntList(1, test);
        test = new IntList(0, test);
        IntList.removeDuplicates(test);
    }
}
