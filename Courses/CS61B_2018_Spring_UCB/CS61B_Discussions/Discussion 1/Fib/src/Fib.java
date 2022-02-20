public class Fib {
    public static int fib(int n)
    {
        if (n == 1){
            return 0;
        }
        if (n == 2){
            return 1;
        }
        return fib(n - 1) + fib(n - 2);
    }

    public static int fibloop(int n)
    {
        if (n == 1){
            return 0;
        }
        if (n == 2){
            return 1;
        }
        int secondprevious = 0;
        int firstprevious = 1;
        int result = 0;
        for (int i = 2; i < n; i++){
            result = firstprevious + secondprevious;
            secondprevious = firstprevious;
            firstprevious = result;
        }
        return result;

    }

    public static void main(String[] args){
        for (int i = 1; i < 10; i++) {
            System.out.println("Fib " + i + ":" + fib(i));
        }

        for (int i = 1; i < 10; i++) {
            System.out.println("Fibloop " + i + ":" + fibloop(i));
        }
    }
}
