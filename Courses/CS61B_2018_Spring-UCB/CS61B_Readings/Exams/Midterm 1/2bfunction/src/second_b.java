public class second_b {
    public static void g(IntList x) {
        if (x == null) { return; }
        g(x.rest);
        x.rest = x;
    }

    public static void main(String[] args) {
        IntList L = IntList.of(3, 4, 5);
        g(L);
    }
}
