package test;

import static org.junit.Assert.*;
import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;
import main.Fibonacci;

@RunWith(Theories.class)
public class FibonacciTheoryTest {
    
    @DataPoints
    public static int[][] data = {
        {0, 0}, {1, 1}, {2, 1}, {3, 2}, {4, 3},
        {5, 5}, {6, 8}, {7, 13}, {8, 21}, {9, 34}
    };
    
    @Theory
    public void testFibonacci(int[] pair) {
        assertEquals(pair[1], Fibonacci.compute(pair[0]));
    }
}


