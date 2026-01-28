package test;

import static org.junit.Assert.*;
import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;
import main.Fibonacci;

// Q8: Rewrite Q5 using JUnit Theories
@RunWith(Theories.class)
public class FibonacciTheoryTest {
    
    // First 10 Fibonacci numbers (index 0 to 9)
    @DataPoints
    public static int[][] fibonacciData = {
        {0, 0}, {1, 1}, {2, 1}, {3, 2}, {4, 3},
        {5, 5}, {6, 8}, {7, 13}, {8, 21}, {9, 34}
    };
    
    @Theory
    public void testFibonacci(int[] data) {
        int index = data[0];
        int expected = data[1];
        assertEquals(expected, Fibonacci.compute(index));
    }
}


