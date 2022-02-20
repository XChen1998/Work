import java.util.Random;

public class Runtime3 {
    static int M = 10;
    static int N = 10;

    public static boolean comeOn() {
        Random seed = new Random();
        int randomnumber = seed.nextInt(10);
        if (randomnumber < 5) {
            return true;
        } else {
            return false;
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < N; i += 1) {
            for (int j = 1; j <= M; ) {
                if (comeOn()) {
                    j += 1;
                } else {
                    j *= 2;
                }
            }
        }
    }
}
