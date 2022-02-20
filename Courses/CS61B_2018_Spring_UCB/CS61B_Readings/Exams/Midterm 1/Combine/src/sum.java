public class sum {
    public static int sumAll(int[] x) { // sumAll is not a member of Combine
        return combine.combine(new Add(), x);
    }

    public static void main(String[] args) {
        int[] x = {1, 2, 3};
        System.out.println(sumAll(x));
    }
}