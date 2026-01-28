package test;

import static org.junit.Assert.*;
import java.util.Arrays;
import java.util.Collection;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import main.PrimeNumberChecker;

@RunWith(Parameterized.class)
public class PrimeNumberCheckerTest {
    
    private int number;
    private boolean expectedResult;
    
    public PrimeNumberCheckerTest(int number, boolean expectedResult) {
        this.number = number;
        this.expectedResult = expectedResult;
    }
    
    @Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
            {2, true}, {6, false}, {19, true}, {22, false}, {23, true}
        });
    }
    
    @Test
    public void testIsPrime() {
        assertEquals(expectedResult, PrimeNumberChecker.isPrime(number));
    }
}
