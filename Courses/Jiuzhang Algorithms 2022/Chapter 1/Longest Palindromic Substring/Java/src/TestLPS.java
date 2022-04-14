public class TestLPS {
    public static void main(String[] args) {
        String[] test = {"abcbe", "b", "bb", "ca", "mata", "fdaslneblkwgbliwqorefdjdlksanczxbvkhfdfdfdfdfdssewdvcxza"};
        MySolution ms = new MySolution();
        CentreMethod2 cm2 = new CentreMethod2();
        DPMethod dp = new DPMethod();
        for (String s : test){
            System.out.println("CurrentString: " + s);
            System.out.println("My solution: " + ms.longestPalindrome(s));
            System.out.println("CentreMethod2: " + cm2.longestPalindrome(s));
            System.out.println("Dynamic Programming Method: " + dp.longestPalindrome(s));
            System.out.println();
        }
    }
}