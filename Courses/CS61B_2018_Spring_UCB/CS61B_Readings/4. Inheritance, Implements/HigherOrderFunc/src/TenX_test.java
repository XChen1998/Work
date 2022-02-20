public class TenX_test {
    /* Returns ten times the argument. */
    public int apply(int x) {
        return 10 * x;
    }

    public static int do_twice(TenX_test f, int x) {
        return f.apply(f.apply(x));
    }

    public static void main(String[] args) {
        System.out.println(do_twice(new TenX_test(), 2));
    }
}