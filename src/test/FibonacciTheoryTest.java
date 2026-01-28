package test;

import static org.junit.Assert.*;
import static org.junit.Assume.*;
import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;
import main.Fibonacci;

@RunWith(Theories.class)
public class FibonacciTheoryTest {
    
    // Data points: pairs of (index, expected Fibonacci value)
    @DataPoints
    public static int[][] fibonacciData = {
        {0, 0},   // F(0) = 0
        {1, 1},   // F(1) = 1
        {2, 1},   // F(2) = 1
        {3, 2},   // F(3) = 2
        {4, 3},   // F(4) = 3
        {5, 5},   // F(5) = 5
        {6, 8},   // F(6) = 8
        {7, 13},  // F(7) = 13
        {8, 21},  // F(8) = 21
        {9, 34}   // F(9) = 34
    };
    
    // Theory: Fibonacci(n) should return expected value
    @Theory
    public void testFibonacci(int[] data) {
        int index = data[0];
        int expected = data[1];
        
        assumeTrue(index >= 0);
        
        assertEquals(expected, Fibonacci.compute(index));
    }
    
    // Theory: Fibonacci property F(n) = F(n-1) + F(n-2) for n >= 2
    @DataPoints
    public static int[] indices = {2, 3, 4, 5, 6, 7, 8, 9};
    
    @Theory
    public void testFibonacciProperty(int n) {
        assumeTrue(n >= 2);
        
        int fn = Fibonacci.compute(n);
        int fn1 = Fibonacci.compute(n - 1);
        int fn2 = Fibonacci.compute(n - 2);
        
        assertEquals("F(n) should equal F(n-1) + F(n-2)", fn, fn1 + fn2);
    }
}
