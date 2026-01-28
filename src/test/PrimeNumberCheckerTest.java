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
    
    // Constructor that takes test data
    public PrimeNumberCheckerTest(int number, boolean expectedResult) {
        this.number = number;
        this.expectedResult = expectedResult;
    }
    
    // Test data: {2, 6, 19, 22, 23}
    @Parameters(name = "{index}: isPrime({0}) = {1}")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
            {2, true},    // 2 is prime
            {6, false},   // 6 is not prime (divisible by 2, 3)
            {19, true},   // 19 is prime
            {22, false},  // 22 is not prime (divisible by 2, 11)
            {23, true}    // 23 is prime
        });
    }
    
    @Test
    public void testIsPrime() {
        assertEquals(expectedResult, PrimeNumberChecker.isPrime(number));
    }
}
