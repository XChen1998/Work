package GoodIntegerStack;

public class GoodIntegerTest {
    public static void main(String[] args) {
        try {
            GoodIntegerStack test = new GoodIntegerStack();
            test.pop();
        }
        catch(NullPointerException error){
            System.out.println("Success!");
        }
        try {
            GoodIntegerStack test2 = new GoodIntegerStack();
            test2.peek();
        }
        catch(NullPointerException error){
            System.out.println("Success!");
        }
    }
}
