package test;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import main.Triangle;

public class TriangleTest {
    
    Triangle t1, t2, t3;
    
    @Before
    public void init() {
        t1 = new Triangle(3, 4, 5);
        t2 = new Triangle(5, 4, 3);
        t3 = new Triangle(8, 5, 5);
    }
    
    @Test
    public void testAreaT1() {
        assertEquals(6.0, t1.calculateArea(), 0.001);
    }
    
    @Test
    public void testAreaT2() {
        assertEquals(6.0, t2.calculateArea(), 0.001);
    }
    
    @Test
    public void testAreaT3() {
        assertEquals(12.0, t3.calculateArea(), 0.001);
    }
    
    @Test
    public void testT1EqualsT2Area() {
        assertEquals(t1.calculateArea(), t2.calculateArea(), 0.001);
    }
    
    @Test
    public void testValidTriangle() {
        Triangle valid = new Triangle(3, 4, 5);
        assertTrue(valid.isValid());
    }
    
    // should fail - sides dont make a triangle
    @Test
    public void testInvalidTriangle() {
        Triangle invalid = new Triangle(3, 4, 100);
        assertFalse(invalid.isValid());
    }
    
    @Test
    public void testInvalidTriangleArea() {
        Triangle invalid = new Triangle(3, 4, 100);
        double area = invalid.calculateArea();
        assertTrue(Double.isNaN(area));
    }
}
