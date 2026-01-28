package test;

import static org.junit.Assert.*;
import static org.junit.Assume.*;
import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;

@RunWith(Theories.class)
public class MathTheoryTest {
    
    // Task 1 & 2: Original value set
    @DataPoints
    public static int[] values = {1, 2, 307, 400567};
    
    // Task 1: Theory that a + b > a and a + b > b for all a, b > 0
    @Theory
    public void testSumGreaterThanParts(int a, int b) {
        assumeTrue(a > 0);
        assumeTrue(b > 0);
        
        assertTrue("a + b should be greater than a", a + b > a);
        assertTrue("a + b should be greater than b", a + b > b);
    }
    
    // Task 2: Theory that a + b = b + a (commutative property)
    @Theory
    public void testCommutativeProperty(int a, int b) {
        assumeTrue(a > 0);
        assumeTrue(b > 0);
        
        assertEquals("a + b should equal b + a", a + b, b + a);
    }
}
