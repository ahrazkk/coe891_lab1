package test;

import static org.junit.Assert.*;
import org.junit.Test;
import main.RE;

public class RETest {
    
    @Test
    public void testValidPhone() {
        assertTrue(RE.checkPhoneNumber("(123)123-1234"));
    }
    
    @Test
    public void testValidPhoneWithSpaces() {
        assertTrue(RE.checkPhoneNumber("(123) 456 - 7890"));
    }
    
    @Test
    public void testInvalidPhone() {
        assertFalse(RE.checkPhoneNumber("123-456-7890"));
    }
}

