package test;

import static org.junit.Assert.*;
import static org.junit.Assume.*;
import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;

@RunWith(Theories.class)
public class MathTheoryTest {
    
    // Task 1-2: val = {1, 2, 307, 400567}
    // Task 3-4: newval = {0, -1, -10, -1234, 1, 10, 6789}
    // Task 5: adds Integer.MAX_VALUE, Integer.MIN_VALUE
    @DataPoints
    public static int[] values = {
        1, 2, 307, 400567,
        0, -1, -10, -1234, 10, 6789,
        Integer.MAX_VALUE, Integer.MIN_VALUE
    };
    
    // Task 1: a + b > a and a + b > b (for a, b > 0)
    // Task 4: Using assumptions to filter invalid cases
    // Task 5: Using assumptions to avoid overflow
    @Theory
    public void testSumGreaterThanParts(int a, int b) {
        assumeTrue(a > 0);
        assumeTrue(b > 0);
        
        long sum = (long) a + (long) b;
        assumeTrue(sum <= Integer.MAX_VALUE);
        
        assertTrue(a + b > a);
        assertTrue(a + b > b);
    }
    
    // Task 2: a + b = b + a (commutative property)
    @Theory
    public void testCommutativeProperty(int a, int b) {
        assertEquals(a + b, b + a);
    }
}



