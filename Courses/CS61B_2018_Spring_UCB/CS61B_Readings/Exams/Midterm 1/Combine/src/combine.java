public class combine {
    public static int combine(ComFunc f, int[] x) {

        if (x.length == 0) {
            return 0;
        }
        if (x.length == 1) {
            return x[0];
        }
        int sum = f.apply(x[0], x[1]);
        return combine(f, x, 2, sum);
    }
// your private helper function cannot create new arrays (too slow)

    private static int combine(ComFunc f, int[] x, int position, int sum) {

        if (position == x.length) {
            return sum;
        }
        sum = f.apply(x[position], sum);
        return combine(f, x, position + 1, sum);

    }
}