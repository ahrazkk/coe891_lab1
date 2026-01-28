package test;

import org.junit.Test;
import static org.junit.Assert.assertArrayEquals;
import main.ArrayMult;

public class ArrayMultTest {
    @Test
    public void testMult() {
        ArrayMult am = new ArrayMult();
        int[] a1 = {2, 3};
        int[] a2 = {4, 5, 6};
        int[] expected = {8, 15, 6}; // (24), (35) and remaining 6 
        assertArrayEquals(expected, am.mult(a1, a2));
    }
}