public class GreedyMethod {
    /**
     * @param gas:  An array of integers
     * @param cost: An array of integers
     * @return: An integer
     */
    public int canCompleteCircuit(int[] gas, int[] cost) {
        if (gas == null || cost == null) {
            return -1;
        }

        int index = 0;
        int sumTotal = 0;
        int sumCur = 0;
        for (int i = 0; i < gas.length; i++) {
            int dif = gas[i] - cost[i];
            sumTotal += dif;
            sumCur += dif;
            if (sumCur < 0) {
                sumCur = 0;
                index = i + 1;
            }
        }
        return sumTotal >= 0 ? index : -1;
    }
}