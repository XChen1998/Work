import java.util.ArrayList;

public class BasicArrayList {
    public static void main(String[] args) {
        ArrayList<Integer> L = new ArrayList<Integer>();
        L.add(5);
        L.add(6);
        int first = L.get(0);
        System.out.println(first);
        Double a = 15.0;
        Integer b = 15;
        double c = (double)b;
        System.out.println(a == c);

    }
}