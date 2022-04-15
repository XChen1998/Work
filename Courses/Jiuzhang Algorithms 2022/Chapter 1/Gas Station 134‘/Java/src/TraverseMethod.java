public class TraverseMethod {
    /**
     * @param gas: An array of integers
     * @param cost: An array of integers
     * @return: An integer
     */
    public int canCompleteCircuit(int[] gas, int[] cost) {
        if (gas == null || cost == null) {
            return - 1;
        }
        int length = gas.length;
        if (length != cost.length) {
            return -1;
        }
        for (int i = 0; i < length; i++) {
            if (gas[i] < cost[i]) {
                continue;
            }
            int curGas = 0;
            boolean flag = true;
            for (int j = i; j < i + length; j++) {
                curGas = curGas + gas[j % length] - cost[j % length];
                if (curGas < 0) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                return i;
            }
        }
        return -1;

    }
}