public class IntAndIntger {

    public static void blah(Integer x) {
        System.out.println(x);
    }

    public static void blahPrimitive(int x) {
        System.out.println(x);
    }

    public static void main(String[] args) {
        int x = 20;
        blah(x);

        Integer y = new Integer(20);
        blahPrimitive(y);
        double a = 15;
        int b = 15;
    }
}
