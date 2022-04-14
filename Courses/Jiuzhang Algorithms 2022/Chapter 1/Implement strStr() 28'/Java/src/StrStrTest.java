public class StrStrTest {
    public static void main(String[] args) {
        String[] source = {"abce", "tecce", "clvkanoeio", "", "ceddd"};
        String[] target = {"ce", "t", "io", "f", "ddda"};
        TraverseMethod tm = new TraverseMethod();
        RKAlgs rka = new RKAlgs();
        for (int i = 0; i < source.length; i++) {
            System.out.println("Current source: " + source[i]);
            System.out.println("Current target: " + target[i]);
            System.out.println("Traverse method: " + tm.strStr(source[i], target[i]));
            System.out.println("RKAlgs method: " + rka.strStr(source[i], target[i]));
            System.out.println();
        }
    }
}
