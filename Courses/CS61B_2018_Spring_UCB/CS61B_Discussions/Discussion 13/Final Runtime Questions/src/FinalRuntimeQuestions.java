public class FinalRuntimeQuestions {

    boolean h(int i) {
        return false;
    }

    /*p1 N^2*/
    int p1(int M) {
        return r(0, M);
    }

    int r(int i, int M) {
        if (i >= M) return 0;
        if (s(i) > 0) return i;
        return r(i + 1, M);
    }

    int s(int k) {
        if (k <= 0) return 0;
        if (h(k)) return k;
        return s(k - 1);
    }

    /*p2 N^2*/
    void p2(int M) {
        int L, U;
        for (L = U = 0; U < M; L += 1, U += 2) {
            for (int i = L; i < U; i += 1) {
                h(i);
            }
        }
    }
}
