public class Widenning {

    public static void blahDouble(double x) {
        System.out.println("double: " + x);
    }

    public static void blahInt(int x) {
        System.out.println("int: " + x);
    }

    public static void main(String[] args) {
        int x = 20;
        blahDouble(x);

        double y = 20;
        blahInt((int) y);
    }
}
