package test;

import static org.junit.Assert.*;
import org.junit.Test;
import main.RE;

public class RETest {
    
    // Test valid phone number (123)123-1234
    @Test
    public void testValidPhone1() {
        assertTrue(RE.checkPhoneNumber("(123)123-1234"));
    }
    
    // Test valid phone number (123) 456 - 7890
    @Test
    public void testValidPhone2() {
        assertTrue(RE.checkPhoneNumber("(123) 456 - 7890"));
    }
    
    // Test invalid phone number
    @Test
    public void testInvalidPhone() {
        assertFalse(RE.checkPhoneNumber("123-456-7890"));
    }
}

