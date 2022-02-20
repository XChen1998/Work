public class second_a {
    public static int f(int x) {
        if (x == 1) {
            return 1;
        }
        int a = 2 * f(x / 2);
        return a;
    }

    public static void main(String[] args) {
        for (int i = 1; i < 10; i++){
            int x = f(i);
            System.out.println(x);
        }
    }
}
