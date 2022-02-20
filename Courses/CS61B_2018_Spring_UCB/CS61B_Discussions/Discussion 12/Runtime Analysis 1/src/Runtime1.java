public class Runtime1 {
    public static int N = 10;
    public static int M = 10;

    public static int ping(int i, int j) {
        return i + j;
    }

    public static void main(String[] args) {
        int j = 0;
        for (int i = N; i > 0; i--) {
            for (; j <= M; j++) {
                if (ping(i, j) > 64) {
                    break;
                }
            }
        }
    }
}
