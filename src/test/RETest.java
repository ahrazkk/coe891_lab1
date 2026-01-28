package test;

import static org.junit.Assert.*;
import org.junit.Test;
import main.RE;

public class RETest {
    
    // Test valid phone number with no spaces
    @Test
    public void testValidPhoneNoSpaces() {
        assertTrue(RE.checkPhoneNumber("(123)456-7890"));
    }
    
    // Test valid phone number with spaces
    @Test
    public void testValidPhoneWithSpaces() {
        assertTrue(RE.checkPhoneNumber("(123) 456 - 7890"));
    }
    
    // Test valid phone number format (123)123-1234
    @Test
    public void testValidPhoneFormat() {
        assertTrue(RE.checkPhoneNumber("(123)123-1234"));
    }
    
    // Negative test: missing parentheses
    @Test
    public void testInvalidNoParentheses() {
        assertFalse(RE.checkPhoneNumber("123-456-7890"));
    }
    
    // Negative test: wrong format
    @Test
    public void testInvalidFormat() {
        assertFalse(RE.checkPhoneNumber("1234567890"));
    }
    
    // Negative test: letters in phone number
    @Test
    public void testInvalidWithLetters() {
        assertFalse(RE.checkPhoneNumber("(abc)def-ghij"));
    }
}
