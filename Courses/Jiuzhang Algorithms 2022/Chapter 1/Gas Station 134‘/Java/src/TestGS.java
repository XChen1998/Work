public class TestGS {
    public static void main(String[] args) {
        int[][] gas = {{1, 2, 3}, {2, 1, 5}, {3, 4, 5, 1, 2, 3, 1}, {9}, {12, 39, 1, 0, 1, 1, 5, 9, 13}};
        int[][] cost = {{3, 1, 2}, {4, 2, 4}, {2, 3, 8, 1, 1, 1, 3}, {1}, {1, 3, 50, 1, 2, 3, 3, 3, 1}};
        TraverseMethod tm = new TraverseMethod();
        GreedyMethod gm = new GreedyMethod();
        for (int i = 0; i < gas.length; i++) {
            System.out.print("Current gas: ");
            for (int g : gas[i]) {
                System.out.print(g + " ");
            }
            System.out.println();
            System.out.print("Current cost: ");
            for (int c : cost[i]) {
                System.out.print(c + " ");
            }
            System.out.println();
            System.out.println("Traverse method: " + tm.canCompleteCircuit(gas[i], cost[i]));
            System.out.println("Greedy method: " + gm.canCompleteCircuit(gas[i], cost[i]));
            System.out.println();
        }
    }
}
