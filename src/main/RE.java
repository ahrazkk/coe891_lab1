package main;

import java.util.Scanner;

public class RE {
    
    public static boolean checkPhoneNumber(String s) {
        // Fixed regex: escaped backslashes, escaped parentheses, allow optional spaces
        return s.matches("\\(\\d{3}\\)\\s*\\d{3}\\s*-\\s*\\d{4}");
    }
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter a phone number: ");
        String input = sc.nextLine();
        boolean wasPhoneNum = checkPhoneNumber(input);
        System.out.println("\nThat was" + (wasPhoneNum ? "" : "n't") + " a phone number.");
        sc.close();
    }
}
