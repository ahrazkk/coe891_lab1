package test;

import static org.junit.Assert.*;
import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;
import main.PrimeNumberChecker;

@RunWith(Theories.class)
public class PrimeNumberCheckerTheoryTest {
    
    @DataPoints
    public static Object[][] data = {
        {2, true}, {6, false}, {19, true}, {22, false}, {23, true}
    };
    
    @Theory
    public void testIsPrime(Object[] pair) {
        assertEquals(pair[1], PrimeNumberChecker.isPrime((Integer) pair[0]));
    }
}


