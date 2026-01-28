package test;

import static org.junit.Assert.*;
import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;
import main.PrimeNumberChecker;

// Q8: Rewrite Q6 using JUnit Theories
@RunWith(Theories.class)
public class PrimeNumberCheckerTheoryTest {
    
    // Test data: {2, 6, 19, 22, 23}
    @DataPoints
    public static Object[][] primeData = {
        {2, true}, {6, false}, {19, true}, {22, false}, {23, true}
    };
    
    @Theory
    public void testIsPrime(Object[] data) {
        int number = (Integer) data[0];
        boolean expected = (Boolean) data[1];
        assertEquals(expected, PrimeNumberChecker.isPrime(number));
    }
}


