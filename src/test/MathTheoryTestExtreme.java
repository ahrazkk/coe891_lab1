package test;

import static org.junit.Assert.*;
import static org.junit.Assume.*;
import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;

@RunWith(Theories.class)
public class MathTheoryTestExtreme {
    
    // Task 5: Value set with Integer.MAX_VALUE and Integer.MIN_VALUE
    @DataPoints
    public static int[] extremeValues = {0, -1, -10, -1234, 1, 10, 6789, 
                                          Integer.MAX_VALUE, Integer.MIN_VALUE};
    
    // Task 5: Theory with assumptions
    // NOTE: This may fail due to integer overflow!
    // Integer.MAX_VALUE + 1 causes overflow and becomes negative
    // So a + b > a may fail when overflow occurs
    @Theory
    public void testSumGreaterThanParts(int a, int b) {
        // Assumptions: only test when both a and b are positive
        assumeTrue(a > 0);
        assumeTrue(b > 0);
        
        // Additional assumption to avoid overflow
        // a + b must not overflow (result must be positive)
        long sum = (long) a + (long) b;
        assumeTrue(sum <= Integer.MAX_VALUE);
        
        assertTrue("a + b should be greater than a", a + b > a);
        assertTrue("a + b should be greater than b", a + b > b);
    }
    
    // Commutative property still works even with overflow
    // because both sides overflow the same way
    @Theory
    public void testCommutativeProperty(int a, int b) {
        assertEquals("a + b should equal b + a", a + b, b + a);
    }
}
