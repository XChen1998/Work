import org.junit.Test;

public class TestSLListVista {

    public static void main(String[] args) {

        SLList<Integer> p = new SLList<>();
        for (int i = 0; i < 10; i++) {
            p.insertFront(10 - i);
        }
        for (int i = 2; i < 12; i++) {
            int index = p.indexOf(i);
            System.out.println(index);
        }

        SLList<Integer> newp = new SLListVista<>();
        for (int i = 0; i < 10; i++) {
            newp.insertFront(10 - i);
        }
        for (int i = 2; i < 12; i++) {
            int index = newp.indexOf(i);
            System.out.println(index);
        }
    }
}
