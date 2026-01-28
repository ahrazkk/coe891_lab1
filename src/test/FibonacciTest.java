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
    
    public FibonacciTest(int index, int expectedResult) {
        this.index = index;
        this.expectedResult = expectedResult;
    }
    
    @Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
            {0, 0}, {1, 1}, {2, 1}, {3, 2}, {4, 3},
            {5, 5}, {6, 8}, {7, 13}, {8, 21}, {9, 34}
        });
    }
    
    @Test
    public void testFibonacci() {
        assertEquals(expectedResult, Fibonacci.compute(index));
    }
}
