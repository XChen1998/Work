import java.sql.ResultSet;

public class TestLPS {
    public static void main(String[] args) {
        String[] test = {"abcbe", "b", "bb", "ca", "mata", "fdaslneblkwgbliwqorefdjdlksanczxbvkhfdfdfdfdfdssewdvcxza"};
        MyStupidSolution mss = new MyStupidSolution();
        DPVersion1 DP1 = new DPVersion1();
        RecursiveMethod RM = new RecursiveMethod();
        for (String s : test){
            System.out.println("CurrentString: " + s);
            System.out.println("My stupid solution: " + mss.longestPalindromeSubseq(s));
            System.out.println("DP version 1: " + DP1.longestPalindromeSubseq(s));
            System.out.println("Recursive method: " + RM.longestPalindromeSubseq(s));

            System.out.println();
        }
    }
}