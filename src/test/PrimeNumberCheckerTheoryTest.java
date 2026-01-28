package test;

import static org.junit.Assert.*;
import static org.junit.Assume.*;
import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;
import main.PrimeNumberChecker;

@RunWith(Theories.class)
public class PrimeNumberCheckerTheoryTest {
    
    // Data points: pairs of (number, expected isPrime result)
    @DataPoints
    public static Object[][] primeData = {
        {2, true},    // 2 is prime
        {6, false},   // 6 is not prime
        {19, true},   // 19 is prime
        {22, false},  // 22 is not prime
        {23, true}    // 23 is prime
    };
    
    // Theory: isPrime(n) should return expected value
    @Theory
    public void testIsPrime(Object[] data) {
        int number = (Integer) data[0];
        boolean expected = (Boolean) data[1];
        
        assumeTrue(number > 0);
        
        assertEquals(expected, PrimeNumberChecker.isPrime(number));
    }
    
    // Theory: All prime numbers greater than 2 are odd
    @DataPoints
    public static int[] numbers = {2, 3, 5, 7, 11, 13, 17, 19, 23};
    
    @Theory
    public void testPrimesGreaterThan2AreOdd(int n) {
        assumeTrue(n > 2);
        assumeTrue(PrimeNumberChecker.isPrime(n));
        
        assertTrue("Primes > 2 should be odd", n % 2 != 0);
    }
    
    // Theory: 1 and numbers less than 1 are not prime
    @DataPoints
    public static int[] nonPositives = {-5, -1, 0, 1};
    
    @Theory
    public void testNonPositivesNotPrime(int n) {
        assumeTrue(n <= 1);
        
        assertFalse("Numbers <= 1 should not be prime", PrimeNumberChecker.isPrime(n));
    }
}
