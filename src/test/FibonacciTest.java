package test;

import static org.junit.Assert.*;
import java.util.Arrays;
import java.util.Collection;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import main.Fibonacci;

@RunWith(Parameterized.class)
public class FibonacciTest {
    
    private int index;
    private int expectedResult;
    
    // Constructor that takes test data
    public FibonacciTest(int index, int expectedResult) {
        this.index = index;
        this.expectedResult = expectedResult;
    }
    
    // Test data: first 10 Fibonacci numbers (index 0 to 9)
    // Fibonacci sequence: 0, 1, 1, 2, 3, 5, 8, 13, 21, 34
    @Parameters(name = "{index}: Fibonacci({0}) = {1}")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
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
        });
    }
    
    @Test
    public void testFibonacci() {
        assertEquals(expectedResult, Fibonacci.compute(index));
    }
}
