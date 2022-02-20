// Java Program to Swap Two values using third variable
// using temp variable

// Importing generic libraries
import java.util.*;

class GFG {

    // Function to swap two numbers
    // Using temporary variable
    static void swapValuesUsingThirdVariable(int m, int n)
    {
        // Swapping the values
        int temp = m;
        m = n;
        n = temp;
        System.out.println("Value of m is " + m
                + " and Value of n is " + n);
    }

    // Main driver code
    public static void main(String[] args)
    {
        // Random integerslues
        int m = 9, n = 5;

        // Calling above function to
        // reverse the numbers
        swapValuesUsingThirdVariable(m, n);
    }
}