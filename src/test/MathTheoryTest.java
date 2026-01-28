package test;

import static org.junit.Assert.*;
import static org.junit.Assume.*;
import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;

@RunWith(Theories.class)
public class MathTheoryTest {
    
    @DataPoints
    public static int[] values = {
        1, 2, 307, 400567,
        0, -1, -10, -1234, 10, 6789,
        Integer.MAX_VALUE, Integer.MIN_VALUE
    };
    
    @Theory
    public void testSumGreaterThanParts(int a, int b) {
        // skip negatives and zero
        assumeTrue(a > 0);
        assumeTrue(b > 0);
        
        // skip overflow cases
        long sum = (long) a + (long) b;
        assumeTrue(sum <= Integer.MAX_VALUE);
        
        assertTrue(a + b > a);
        assertTrue(a + b > b);
    }
    
    @Theory
    public void testCommutativeProperty(int a, int b) {
        assertEquals(a + b, b + a);
    }
}



