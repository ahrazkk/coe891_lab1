package main;

public class Triangle {
    public int side1, side2, side3;

    public Triangle(int side1, int side2, int side3) {
        this.side1 = side1;
        this.side2 = side2;
        this.side3 = side3;
    }

    public double calculateArea() {
        // Heron's Formula for area of a triangle (FIXED)
        double s = (side1 + side2 + side3) * 0.5;
        // Fixed: should be (s - side) not (side - s)
        double result = Math.sqrt(s * (s - side1) * (s - side2) * (s - side3));
        return result;
    }

    // Method to check if the triangle is valid
    public boolean isValid() {
        return (side1 + side2 > side3) && (side1 + side3 > side2) && (side2 + side3 > side1);
    }
}
