package test;

import static org.junit.Assert.*;
import static org.junit.Assume.*;
import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;

@RunWith(Theories.class)
public class MathTheoryTestNewVal {
    
    // Task 3 & 4: New value set with negatives and zero
    @DataPoints
    public static int[] newval = {0, -1, -10, -1234, 1, 10, 6789};
    
    // Task 3 & 4: Theory with assumptions to handle negative values
    // The theory a + b > a and a + b > b only holds when a > 0 AND b > 0
    // Assumptions filter out invalid cases
    @Theory
    public void testSumGreaterThanParts(int a, int b) {
        // Assumptions: only test when both a and b are positive
        assumeTrue(a > 0);
        assumeTrue(b > 0);
        
        assertTrue("a + b should be greater than a", a + b > a);
        assertTrue("a + b should be greater than b", a + b > b);
    }
    
    // Commutative property works for all integers (no assumptions needed)
    @Theory
    public void testCommutativeProperty(int a, int b) {
        assertEquals("a + b should equal b + a", a + b, b + a);
    }
}
