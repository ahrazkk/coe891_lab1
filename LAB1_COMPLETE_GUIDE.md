# COE891 Lab 1: Complete Beginner's Guide
## JUnit Testing, Parameterized Tests, and Theories

---

# Table of Contents
1. [Q1: ArrayMult - Basic JUnit Testing](#q1-arraymult---basic-junit-testing)
2. [Q2: Triangle - Debugging & JUnit](#q2-triangle---debugging--junit)
3. [Q3: RE (Regular Expressions) - Regex & JUnit](#q3-re-regular-expressions---regex--junit)
4. [Q4: TestSuite - Running Multiple Tests Together](#q4-testsuite---running-multiple-tests-together)
5. [Q5: Fibonacci - Parameterized Tests](#q5-fibonacci---parameterized-tests)
6. [Q6: PrimeNumberChecker - Parameterized Tests](#q6-primenumberchecker---parameterized-tests)
7. [Q7: Math Theories - JUnit Theories](#q7-math-theories---junit-theories)
8. [Q8: Fibonacci & Prime Theories - Rewriting with Theories](#q8-fibonacci--prime-theories---rewriting-with-theories)

---

# Q1: ArrayMult - Basic JUnit Testing

## What the Lab Asked

> Implement a class `ArrayMult` which carries out point-wise multiplication on two arrays of non-negative integers, and implement a JUnit test `ArrayMultTest` to check that your code does what it is supposed to do.

**Requirements:**
- Arrays can be different lengths
- Output array length = longest input array length
- Overlapping elements are multiplied
- Remaining elements are copied from the longer array

---

## The Main Code: `ArrayMult.java`

```java
package main;

public class ArrayMult {
    public int[] mult(int[] array1, int[] array2) {
        int maxLen = Math.max(array1.length, array2.length); 
        int minLen = Math.min(array1.length, array2.length); 
        int[] outArray = new int[maxLen];

        // multiply overlapping elements only
        for (int i = 0; i < minLen; i++) {
            outArray[i] = array1[i] * array2[i];
        }

        // copy remaining elements from the longer array to outarray
        int[] longArray = (array1.length > array2.length) ? array1 : array2;
        for (int j = minLen; j < maxLen; j++) {
            outArray[j] = longArray[j];
        }
        return outArray;
    }
}
```

### Line-by-Line Explanation

| Line | Code | What It Does |
|------|------|--------------|
| 1 | `package main;` | Declares this class belongs to the `main` package (organizes code) |
| 3 | `public class ArrayMult {` | Declares a public class named `ArrayMult` |
| 4 | `public int[] mult(int[] array1, int[] array2) {` | Method that takes 2 integer arrays and returns 1 integer array |
| 5 | `int maxLen = Math.max(array1.length, array2.length);` | Finds the length of the LONGER array |
| 6 | `int minLen = Math.min(array1.length, array2.length);` | Finds the length of the SHORTER array |
| 7 | `int[] outArray = new int[maxLen];` | Creates output array with size = longest array |
| 10-12 | `for (int i = 0; i < minLen; i++) {...}` | Loop through overlapping positions and multiply |
| 15 | `int[] longArray = (condition) ? array1 : array2;` | Ternary operator - picks whichever array is longer |
| 16-18 | `for (int j = minLen; j < maxLen; j++) {...}` | Copy remaining elements from longer array |
| 19 | `return outArray;` | Return the completed result |

### Visual Example

```
Input:  array1 = [2, 3]      (length 2)
        array2 = [4, 5, 6]   (length 3)

Step 1: Calculate lengths
        maxLen = Math.max(2, 3) = 3
        minLen = Math.min(2, 3) = 2

Step 2: Create output array
        outArray = [0, 0, 0]  (size 3, initialized with zeros)

Step 3: Multiply overlapping elements (i = 0 to minLen-1)
        i = 0: outArray[0] = array1[0] * array2[0] = 2 * 4 = 8
        i = 1: outArray[1] = array1[1] * array2[1] = 3 * 5 = 15
        outArray = [8, 15, 0]

Step 4: Find longer array
        array1.length (2) > array2.length (3)? NO
        So longArray = array2 = [4, 5, 6]

Step 5: Copy remaining elements (j = minLen to maxLen-1)
        j = 2: outArray[2] = longArray[2] = 6
        outArray = [8, 15, 6]

Output: [8, 15, 6] ✓
```

---

## The Test Code: `ArrayMultTest.java`

```java
package test;

import org.junit.Test;
import static org.junit.Assert.assertArrayEquals;
import main.ArrayMult;

public class ArrayMultTest {
    @Test
    public void testMult() {
        ArrayMult am = new ArrayMult();
        int[] a1 = {2, 3};
        int[] a2 = {4, 5, 6};
        int[] expected = {8, 15, 6};
        assertArrayEquals(expected, am.mult(a1, a2));
    }
}
```

### Line-by-Line Explanation

| Line | Code | What It Does |
|------|------|--------------|
| 1 | `package test;` | Declares this class belongs to `test` package |
| 3 | `import org.junit.Test;` | Imports JUnit's @Test annotation |
| 4 | `import static org.junit.Assert.assertArrayEquals;` | Static import - lets us call `assertArrayEquals()` directly |
| 5 | `import main.ArrayMult;` | Imports the class we're testing |
| 7 | `public class ArrayMultTest {` | Test class name = ClassName + "Test" |
| 8 | `@Test` | Annotation telling JUnit this method is a test case |
| 9 | `public void testMult() {` | Test method name = "test" + MethodName |
| 10 | `ArrayMult am = new ArrayMult();` | Create an object of the class we're testing |
| 11-12 | `int[] a1 = {...}; int[] a2 = {...};` | **Arrange** - Set up test data |
| 13 | `int[] expected = {8, 15, 6};` | What we EXPECT the result to be |
| 14 | `assertArrayEquals(expected, am.mult(a1, a2));` | **Assert** - Compare expected vs actual |

### The AAA Pattern

```
ARRANGE → Set up test data (a1, a2, expected)
ACT     → Call the method (am.mult(a1, a2))
ASSERT  → Check the result (assertArrayEquals)
```

---

## Demo Questions for Q1

| Question | Answer |
|----------|--------|
| **Why use `assertArrayEquals` instead of `assertEquals`?** | `assertEquals` compares object references (memory addresses), not contents. `assertArrayEquals` compares each element of the arrays. |
| **What does the ternary operator do?** | `condition ? valueIfTrue : valueIfFalse` - It's a shorthand for if-else that returns a value. |
| **Why is expected value first in assertions?** | JUnit reports "expected X but found Y" - putting expected first makes error messages clearer. |
| **What happens if both arrays are the same length?** | `minLen == maxLen`, so no copying happens - only multiplication. |
| **What happens if one array is empty?** | `minLen = 0`, no multiplication occurs. All elements copied from the non-empty array. |
| **What does @Test do?** | Tells JUnit runner to execute this method as a test case and report pass/fail. |

---

# Q2: Triangle - Debugging & JUnit

## What the Lab Asked

> We have a Triangle class that contains a calculateArea method. The mathematical equation is INCORRECT as Heron's formula. Debug and fix the issues.

**Tasks:**
1. Create initialization function with @Before for triangles t1(3,4,5), t2(5,4,3), t3(8,5,5)
2. Fix Heron's formula bug
3. Write test methods for areas of t1, t2, t3
4. Verify t1 and t2 have same area
5. Write positive and negative test cases
6. Handle invalid triangle like (3,4,100)

---

## The Bug in Original Code

**Original (WRONG):**
```java
double result = Math.sqrt(s * (side1 - s) * (side2 - s) * (side3 - s));
//                            ^^^^^^^^^ WRONG ORDER!
```

**Fixed (CORRECT):**
```java
double result = Math.sqrt(s * (s - side1) * (s - side2) * (s - side3));
//                            ^^^^^^^^^ CORRECT ORDER!
```

**Heron's Formula:** Area = √(s(s-a)(s-b)(s-c)) where s = (a+b+c)/2

---

## The Main Code: `Triangle.java`

```java
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
```

### Line-by-Line Explanation

| Line | Code | What It Does |
|------|------|--------------|
| 4 | `public int side1, side2, side3;` | Instance variables for the three sides |
| 6-10 | Constructor | Initializes the triangle with given side lengths |
| 14 | `double s = (side1 + side2 + side3) * 0.5;` | Calculate semi-perimeter (half of perimeter) |
| 16 | `Math.sqrt(s * (s - side1) * ...)` | Heron's formula: √(s(s-a)(s-b)(s-c)) |
| 22 | `isValid()` | Checks triangle inequality theorem |

### Visual Example: Heron's Formula

```
Triangle: sides = (3, 4, 5)

Step 1: Calculate semi-perimeter
        s = (3 + 4 + 5) / 2 = 12 / 2 = 6

Step 2: Calculate each factor
        (s - side1) = 6 - 3 = 3
        (s - side2) = 6 - 4 = 2
        (s - side3) = 6 - 5 = 1

Step 3: Multiply all factors
        s * (s-a) * (s-b) * (s-c) = 6 * 3 * 2 * 1 = 36

Step 4: Take square root
        √36 = 6

Area = 6.0 ✓ (This is correct for a 3-4-5 right triangle!)
```

### Triangle Inequality Theorem

A valid triangle must satisfy: **The sum of any two sides must be greater than the third side.**

```
Valid Triangle (3, 4, 5):
  3 + 4 = 7 > 5 ✓
  3 + 5 = 8 > 4 ✓
  4 + 5 = 9 > 3 ✓
  All conditions pass → VALID

Invalid Triangle (3, 4, 100):
  3 + 4 = 7 > 100? NO ✗
  First condition fails → INVALID
```

---

## The Test Code: `TriangleTest.java`

```java
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
    
    // Test area of t1 (3,4,5 right triangle, area = 6)
    @Test
    public void testAreaT1() {
        assertEquals(6.0, t1.calculateArea(), 0.001);
    }
    
    // Test area of t2 (5,4,3 same as t1, area = 6)
    @Test
    public void testAreaT2() {
        assertEquals(6.0, t2.calculateArea(), 0.001);
    }
    
    // Test area of t3 (8,5,5 isoceles triangle, area = 12)
    @Test
    public void testAreaT3() {
        assertEquals(12.0, t3.calculateArea(), 0.001);
    }
    
    // Test that t1 and t2 have the same area
    @Test
    public void testT1EqualsT2Area() {
        assertEquals(t1.calculateArea(), t2.calculateArea(), 0.001);
    }
    
    // Positive test: valid triangle
    @Test
    public void testValidTriangle() {
        Triangle valid = new Triangle(3, 4, 5);
        assertTrue(valid.isValid());
    }
    
    // Negative test: invalid triangle (3,4,100 violates triangle inequality)
    @Test
    public void testInvalidTriangle() {
        Triangle invalid = new Triangle(3, 4, 100);
        assertFalse(invalid.isValid());
    }
    
    // Negative test: invalid triangle returns NaN for area
    @Test
    public void testInvalidTriangleArea() {
        Triangle invalid = new Triangle(3, 4, 100);
        double area = invalid.calculateArea();
        assertTrue(Double.isNaN(area));
    }
}
```

### Key Annotations Explained

| Annotation | When It Runs | Purpose |
|------------|--------------|---------|
| `@Before` | Before EACH @Test method | Set up test data (runs 7 times - once before each test) |
| `@Test` | When running tests | Marks method as a test case |
| `@After` | After EACH @Test method | Cleanup (not used here) |
| `@BeforeClass` | Once before ALL tests | One-time setup |
| `@AfterClass` | Once after ALL tests | One-time cleanup |

### Why @Before?

```
Without @Before:
  Each test would need to create t1, t2, t3 → Duplicate code!

With @Before:
  init() runs automatically before each test
  Triangles are fresh for each test (no interference)
```

### Positive vs Negative Tests

| Type | Purpose | Example |
|------|---------|---------|
| **Positive** | Shows code works correctly for valid input | `testValidTriangle()` - valid triangle returns true |
| **Negative** | Shows code handles invalid input correctly | `testInvalidTriangle()` - invalid triangle returns false |

---

## Demo Questions for Q2

| Question | Answer |
|----------|--------|
| **What was the bug in the original formula?** | Order was wrong: `(side - s)` instead of `(s - side)`. This gives negative numbers under the square root. |
| **Why does assertEquals have a third parameter (0.001)?** | It's the "delta" - allowed difference for floating point comparison. Computers can't represent decimals perfectly. |
| **What happens when you calculate area of an invalid triangle?** | You get `NaN` (Not a Number) because you're taking square root of a negative number. |
| **Why use @Before instead of putting setup code in each test?** | Avoids duplicate code. Changes to setup only need to happen in one place. |
| **What is the triangle inequality theorem?** | Sum of any two sides must be greater than the third side for a valid triangle. |

---

# Q3: RE (Regular Expressions) - Regex & JUnit

## What the Lab Asked

> Debug and fix the compilation error in a phone number validation regex. The task is to verify whether a phone number is valid.

**Original (broken) regex:** `"(\d{3}) \d{3} - \d{4}"`

**Problems to fix:**
1. `\d` in Java strings needs to be `\\d`
2. Parentheses are special regex characters - need to escape them
3. Allow optional whitespace

---

## The Main Code: `RE.java`

```java
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
```

### The Regex Breakdown

**Regex:** `\\(\\d{3}\\)\\s*\\d{3}\\s*-\\s*\\d{4}`

| Part | Meaning | Matches |
|------|---------|---------|
| `\\(` | Literal opening parenthesis | `(` |
| `\\d{3}` | Exactly 3 digits | `123` |
| `\\)` | Literal closing parenthesis | `)` |
| `\\s*` | Zero or more whitespace characters | ` ` or nothing |
| `\\d{3}` | Exactly 3 digits | `456` |
| `\\s*` | Zero or more whitespace characters | ` ` or nothing |
| `-` | Literal hyphen | `-` |
| `\\s*` | Zero or more whitespace characters | ` ` or nothing |
| `\\d{4}` | Exactly 4 digits | `7890` |

### Why Double Backslash?

```
In Regex:  \d means "any digit"
In Java:   \ is an escape character
           \d would try to escape 'd' (invalid!)
           
Solution:  \\ in Java string = single \ in the actual string
           So \\d in Java = \d in regex = "any digit"
```

### Visual Example

```
Input: "(123) 456 - 7890"

Regex: \\(\\d{3}\\)\\s*\\d{3}\\s*-\\s*\\d{4}

Matching:
  \\(     matches "("
  \\d{3}  matches "123"
  \\)     matches ")"
  \\s*    matches " " (one space)
  \\d{3}  matches "456"
  \\s*    matches " " (one space)
  -       matches "-"
  \\s*    matches " " (one space)
  \\d{4}  matches "7890"

Result: MATCH ✓ → Returns true
```

```
Input: "123-456-7890" (no parentheses)

Regex: \\(\\d{3}\\)\\s*\\d{3}\\s*-\\s*\\d{4}

Matching:
  \\(     expects "(" but finds "1"
  
Result: NO MATCH ✗ → Returns false
```

---

## The Test Code: `RETest.java`

```java
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
```

### Test Cases Summary

| Test | Input | Expected | Why |
|------|-------|----------|-----|
| `testValidPhoneNoSpaces` | `(123)456-7890` | true | Correct format, no spaces |
| `testValidPhoneWithSpaces` | `(123) 456 - 7890` | true | Correct format with spaces |
| `testValidPhoneFormat` | `(123)123-1234` | true | Lab example format |
| `testInvalidNoParentheses` | `123-456-7890` | false | Missing parentheses |
| `testInvalidFormat` | `1234567890` | false | Just digits, no formatting |
| `testInvalidWithLetters` | `(abc)def-ghij` | false | Letters instead of digits |

---

## Demo Questions for Q3

| Question | Answer |
|----------|--------|
| **Why use `\\d` instead of `\d`?** | In Java strings, `\` is an escape character. `\\` produces a single `\` in the actual string, which regex then interprets as `\d` (any digit). |
| **What does `\d` mean in regex?** | Matches any digit (0-9). Equivalent to `[0-9]`. |
| **What does `\s*` mean?** | `\s` = whitespace (space, tab, newline). `*` = zero or more. So `\s*` = zero or more whitespace characters. |
| **Why escape parentheses with `\\(`?** | In regex, `()` are special characters for grouping. To match literal parentheses, you must escape them with `\`. |
| **What does `.matches()` do?** | Returns true if the ENTIRE string matches the regex pattern. |

---

# Q4: TestSuite - Running Multiple Tests Together

## What the Lab Asked

> Create and submit a test suit class with its test programs to be run in a suit mode from the programs of the Questions 2 and 3.

A **Test Suite** runs multiple test classes together in one execution.

---

## The Code: `TestSuite.java`

```java
package test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({
    TriangleTest.class,
    RETest.class
})
public class TestSuite {
    // This class remains empty
    // It is used only as a holder for the above annotations
}
```

### Line-by-Line Explanation

| Line | Code | What It Does |
|------|------|--------------|
| 3 | `import org.junit.runner.RunWith;` | Import annotation to specify custom test runner |
| 4 | `import org.junit.runners.Suite;` | Import the Suite runner class |
| 5 | `import org.junit.runners.Suite.SuiteClasses;` | Import annotation to list classes in suite |
| 7 | `@RunWith(Suite.class)` | Tell JUnit to use Suite runner (not default) |
| 8-11 | `@SuiteClasses({...})` | List of test classes to include |
| 12-15 | `public class TestSuite {}` | Empty class - just holds annotations |

### Visual Example

```
Running TestSuite:

TestSuite
├── TriangleTest (7 tests)
│   ├── testAreaT1 ✓
│   ├── testAreaT2 ✓
│   ├── testAreaT3 ✓
│   ├── testT1EqualsT2Area ✓
│   ├── testValidTriangle ✓
│   ├── testInvalidTriangle ✓
│   └── testInvalidTriangleArea ✓
└── RETest (6 tests)
    ├── testValidPhoneNoSpaces ✓
    ├── testValidPhoneWithSpaces ✓
    ├── testValidPhoneFormat ✓
    ├── testInvalidNoParentheses ✓
    ├── testInvalidFormat ✓
    └── testInvalidWithLetters ✓

Total: 13 tests passed ✓
```

### How to Run

```bash
java -cp "bin;lib/*" org.junit.runner.JUnitCore test.TestSuite
```

---

## Demo Questions for Q4

| Question | Answer |
|----------|--------|
| **Why use a Test Suite?** | Run all related tests at once, organize tests, useful for CI/CD pipelines. |
| **Why is the class empty?** | It's just a holder for annotations. JUnit reads the annotations to know what to run. |
| **What does @RunWith(Suite.class) do?** | Tells JUnit to use the Suite runner instead of the default runner. |
| **Can you add more test classes to the suite?** | Yes! Just add them to the @SuiteClasses list: `{TriangleTest.class, RETest.class, NewTest.class}` |

---

# Q5: Fibonacci - Parameterized Tests

## What the Lab Asked

> Write a testing program for the Fibonacci program to test the computation of the first 10 Fibonacci numbers (index from 0 to 9) using parameterized testing with constructor.

**Fibonacci Sequence:** 0, 1, 1, 2, 3, 5, 8, 13, 21, 34, ...

Each number = sum of the two before it.

---

## The Main Code: `Fibonacci.java`

```java
package main;

public class Fibonacci {
    
    public static int compute(int n) {
        int result = 0;
        if (n <= 1) {
            result = n;
        } else {
            result = compute(n - 1) + compute(n - 2);
        }
        return result;
    }
}
```

### Line-by-Line Explanation

| Line | Code | What It Does |
|------|------|--------------|
| 5 | `public static int compute(int n)` | Static method - can call without creating object |
| 6 | `int result = 0;` | Initialize result |
| 7-8 | `if (n <= 1) { result = n; }` | Base case: F(0)=0, F(1)=1 |
| 9-10 | `else { result = compute(n-1) + compute(n-2); }` | Recursive case: F(n) = F(n-1) + F(n-2) |
| 12 | `return result;` | Return the computed Fibonacci number |

### Visual Example: Recursion

```
compute(4)
├── compute(3)
│   ├── compute(2)
│   │   ├── compute(1) → 1
│   │   └── compute(0) → 0
│   │   └── returns 1 + 0 = 1
│   └── compute(1) → 1
│   └── returns 1 + 1 = 2
└── compute(2)
    ├── compute(1) → 1
    └── compute(0) → 0
    └── returns 1 + 0 = 1
└── returns 2 + 1 = 3

F(4) = 3 ✓
```

---

## The Test Code: `FibonacciTest.java`

```java
package test;

import static org.junit.Assert.*;
import java.util.Arrays;
import java.util.Collection;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import main.Fibonacci;

@RunWith(Parameterized.class)
public class FibonacciTest {
    
    private int index;
    private int expectedResult;
    
    // Constructor that takes test data
    public FibonacciTest(int index, int expectedResult) {
        this.index = index;
        this.expectedResult = expectedResult;
    }
    
    // Test data: first 10 Fibonacci numbers (index 0 to 9)
    @Parameters(name = "{index}: Fibonacci({0}) = {1}")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
            {0, 0},   // F(0) = 0
            {1, 1},   // F(1) = 1
            {2, 1},   // F(2) = 1
            {3, 2},   // F(3) = 2
            {4, 3},   // F(4) = 3
            {5, 5},   // F(5) = 5
            {6, 8},   // F(6) = 8
            {7, 13},  // F(7) = 13
            {8, 21},  // F(8) = 21
            {9, 34}   // F(9) = 34
        });
    }
    
    @Test
    public void testFibonacci() {
        assertEquals(expectedResult, Fibonacci.compute(index));
    }
}
```

### The 5 Steps of Parameterized Testing

```
Step 1: @RunWith(Parameterized.class)
        → Tell JUnit to use Parameterized runner

Step 2: Create instance variables
        → private int index, expectedResult;

Step 3: Create constructor
        → Takes one "row" of test data

Step 4: @Parameters method
        → Returns Collection of test data arrays

Step 5: @Test method
        → Uses instance variables for testing
```

### Visual Example: How Parameterized Tests Work

```
@Parameters returns:
  [ {0,0}, {1,1}, {2,1}, {3,2}, {4,3}, {5,5}, {6,8}, {7,13}, {8,21}, {9,34} ]

JUnit runs testFibonacci() 10 times:

Run 1: Constructor(0, 0)  → testFibonacci(): assertEquals(0, Fibonacci.compute(0)) ✓
Run 2: Constructor(1, 1)  → testFibonacci(): assertEquals(1, Fibonacci.compute(1)) ✓
Run 3: Constructor(2, 1)  → testFibonacci(): assertEquals(1, Fibonacci.compute(2)) ✓
Run 4: Constructor(3, 2)  → testFibonacci(): assertEquals(2, Fibonacci.compute(3)) ✓
Run 5: Constructor(4, 3)  → testFibonacci(): assertEquals(3, Fibonacci.compute(4)) ✓
Run 6: Constructor(5, 5)  → testFibonacci(): assertEquals(5, Fibonacci.compute(5)) ✓
Run 7: Constructor(6, 8)  → testFibonacci(): assertEquals(8, Fibonacci.compute(6)) ✓
Run 8: Constructor(7, 13) → testFibonacci(): assertEquals(13, Fibonacci.compute(7)) ✓
Run 9: Constructor(8, 21) → testFibonacci(): assertEquals(21, Fibonacci.compute(8)) ✓
Run 10: Constructor(9, 34) → testFibonacci(): assertEquals(34, Fibonacci.compute(9)) ✓

All 10 tests passed! ✓
```

---

## Demo Questions for Q5

| Question | Answer |
|----------|--------|
| **Why use parameterized tests?** | Avoid writing 10 separate test methods. Same test logic, different data. |
| **What does @Parameters return?** | A Collection of Object arrays. Each array is one "row" of test data. |
| **Why do we need a constructor?** | JUnit calls the constructor for each data row to set up instance variables. |
| **What does `{index}` in the name mean?** | Current test index (0, 1, 2...). `{0}` and `{1}` are the first and second parameters. |
| **What is the Fibonacci formula?** | F(n) = F(n-1) + F(n-2), with base cases F(0)=0, F(1)=1. |

---

# Q6: PrimeNumberChecker - Parameterized Tests

## What the Lab Asked

> Write a Java program for checking if an integer number is a prime number and returning a boolean result. Then, write a testing program to test if integer numbers {2, 6, 19, 22, 23} are prime numbers or not, using parameterized testing.

---

## The Main Code: `PrimeNumberChecker.java`

```java
package main;

public class PrimeNumberChecker {
    
    public static boolean isPrime(int number) {
        if (number <= 1) {
            return false;
        }
        if (number <= 3) {
            return true;
        }
        if (number % 2 == 0) {
            return false;
        }
        for (int i = 3; i <= Math.sqrt(number); i += 2) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }
}
```

### Line-by-Line Explanation

| Line | Code | What It Does |
|------|------|--------------|
| 6-7 | `if (number <= 1) return false;` | 0, 1, and negatives are not prime |
| 9-10 | `if (number <= 3) return true;` | 2 and 3 are prime |
| 12-13 | `if (number % 2 == 0) return false;` | Even numbers > 2 are not prime |
| 15-18 | `for (int i = 3; ...)` | Check odd divisors up to √number |
| 20 | `return true;` | No divisors found → it's prime |

### Visual Example

```
isPrime(23):

Step 1: 23 <= 1? NO → continue
Step 2: 23 <= 3? NO → continue
Step 3: 23 % 2 == 0? NO (23 is odd) → continue
Step 4: Check divisors from 3 to √23 ≈ 4.8
        i = 3: 23 % 3 = 2 (not 0) → continue
        i = 5: 5 > 4.8 → loop ends
Step 5: No divisors found → return true

23 is PRIME ✓
```

```
isPrime(22):

Step 1: 22 <= 1? NO → continue
Step 2: 22 <= 3? NO → continue
Step 3: 22 % 2 == 0? YES (22 is even) → return false

22 is NOT PRIME ✓
```

---

## The Test Code: `PrimeNumberCheckerTest.java`

```java
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
```

### Visual Example: Test Execution

```
Test Data: {2,true}, {6,false}, {19,true}, {22,false}, {23,true}

Run 1: isPrime(2)  → true  == true  ✓
Run 2: isPrime(6)  → false == false ✓
Run 3: isPrime(19) → true  == true  ✓
Run 4: isPrime(22) → false == false ✓
Run 5: isPrime(23) → true  == true  ✓

All 5 tests passed! ✓
```

---

## Demo Questions for Q6

| Question | Answer |
|----------|--------|
| **Why check up to √n instead of n?** | If n has a divisor > √n, it must have a corresponding divisor < √n. Checking to √n is sufficient and faster. |
| **Why skip even numbers in the loop?** | We already checked if n is even. Remaining candidates are odd, so we only check odd divisors. |
| **Is 1 a prime number?** | No. By definition, primes have exactly 2 factors (1 and itself). 1 only has 1 factor. |
| **Is 2 a prime number?** | Yes. It's the only even prime number. |
| **What makes parameterized tests better than individual tests?** | Less code duplication, easier to add new test cases, same logic for all data. |

---

# Q7: Math Theories - JUnit Theories

## What the Lab Asked

> Consider the following mathematical theory: For all a, b > 0, the following is true: a + b > a and a + b > b.

**Tasks:**
1. Test with values {1, 2, 307, 400567}
2. Add commutative property test: a + b = b + a
3. Test with {0, -1, -10, -1234, 1, 10, 6789} - what happens?
4. Use Assumptions to handle negative values
5. Test with Integer.MAX_VALUE and Integer.MIN_VALUE - what happens?

---

## Understanding Theories vs Parameterized Tests

| Parameterized Tests | Theories |
|---------------------|----------|
| Tests specific input-output pairs | Tests properties that should always be true |
| You define exact expected results | You define conditions/properties |
| `@Parameters` returns data | `@DataPoints` provides values |
| Constructor receives data | Theory method receives parameters |

---

## Task 1 & 2: `MathTheoryTest.java`

```java
package test;

import static org.junit.Assert.*;
import static org.junit.Assume.*;
import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;

@RunWith(Theories.class)
public class MathTheoryTest {
    
    @DataPoints
    public static int[] values = {1, 2, 307, 400567};
    
    // Task 1: Theory that a + b > a and a + b > b for all a, b > 0
    @Theory
    public void testSumGreaterThanParts(int a, int b) {
        assumeTrue(a > 0);
        assumeTrue(b > 0);
        
        assertTrue("a + b should be greater than a", a + b > a);
        assertTrue("a + b should be greater than b", a + b > b);
    }
    
    // Task 2: Theory that a + b = b + a (commutative property)
    @Theory
    public void testCommutativeProperty(int a, int b) {
        assumeTrue(a > 0);
        assumeTrue(b > 0);
        
        assertEquals("a + b should equal b + a", a + b, b + a);
    }
}
```

### How Theories Work

```
@DataPoints: {1, 2, 307, 400567}

JUnit runs testSumGreaterThanParts with EVERY COMBINATION:

(a=1, b=1):       1 + 1 = 2 > 1 ✓ and 2 > 1 ✓
(a=1, b=2):       1 + 2 = 3 > 1 ✓ and 3 > 2 ✓
(a=1, b=307):     1 + 307 = 308 > 1 ✓ and 308 > 307 ✓
(a=1, b=400567):  1 + 400567 = 400568 > 1 ✓ and 400568 > 400567 ✓
(a=2, b=1):       2 + 1 = 3 > 2 ✓ and 3 > 1 ✓
(a=2, b=2):       2 + 2 = 4 > 2 ✓ and 4 > 2 ✓
... and so on for all 16 combinations (4 × 4)
```

---

## Task 3 & 4: `MathTheoryTestNewVal.java`

```java
package test;

import static org.junit.Assert.*;
import static org.junit.Assume.*;
import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;

@RunWith(Theories.class)
public class MathTheoryTestNewVal {
    
    @DataPoints
    public static int[] newval = {0, -1, -10, -1234, 1, 10, 6789};
    
    @Theory
    public void testSumGreaterThanParts(int a, int b) {
        // Assumptions: only test when both a and b are positive
        assumeTrue(a > 0);
        assumeTrue(b > 0);
        
        assertTrue("a + b should be greater than a", a + b > a);
        assertTrue("a + b should be greater than b", a + b > b);
    }
    
    @Theory
    public void testCommutativeProperty(int a, int b) {
        assertEquals("a + b should equal b + a", a + b, b + a);
    }
}
```

### What Happens with Negative Values?

```
Without assumptions, the theory a + b > a would FAIL:

a = -1, b = 1:
  a + b = -1 + 1 = 0
  Is 0 > -1? YES ✓
  Is 0 > 1? NO ✗  ← FAILS!

With assumptions:
  assumeTrue(a > 0) → a = -1 fails assumption
  Test is SKIPPED (not failed)
  Only positive values are tested
```

### Assume vs Assert

| Method | If Condition is False |
|--------|----------------------|
| `assertTrue(...)` | Test FAILS with error |
| `assumeTrue(...)` | Test is SKIPPED (ignored) |

---

## Task 5: `MathTheoryTestExtreme.java`

```java
package test;

import static org.junit.Assert.*;
import static org.junit.Assume.*;
import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;

@RunWith(Theories.class)
public class MathTheoryTestExtreme {
    
    @DataPoints
    public static int[] extremeValues = {0, -1, -10, -1234, 1, 10, 6789, 
                                          Integer.MAX_VALUE, Integer.MIN_VALUE};
    
    @Theory
    public void testSumGreaterThanParts(int a, int b) {
        assumeTrue(a > 0);
        assumeTrue(b > 0);
        
        // Additional assumption to avoid overflow
        long sum = (long) a + (long) b;
        assumeTrue(sum <= Integer.MAX_VALUE);
        
        assertTrue("a + b should be greater than a", a + b > a);
        assertTrue("a + b should be greater than b", a + b > b);
    }
    
    @Theory
    public void testCommutativeProperty(int a, int b) {
        assertEquals("a + b should equal b + a", a + b, b + a);
    }
}
```

### Integer Overflow Problem

```
Integer.MAX_VALUE = 2,147,483,647

What happens with:
a = Integer.MAX_VALUE = 2,147,483,647
b = 1

a + b = 2,147,483,647 + 1 = ???

In 32-bit integer: This OVERFLOWS!
Result = -2,147,483,648 (wraps around to negative!)

Is -2,147,483,648 > 2,147,483,647? NO!
Theory FAILS without overflow protection.

Solution: Cast to long before adding, check if result fits in int.
```

---

## Demo Questions for Q7

| Question | Answer |
|----------|--------|
| **What's the difference between Theories and Parameterized tests?** | Theories test properties with all combinations of data points. Parameterized tests test specific input-output pairs. |
| **What does @DataPoints do?** | Provides a set of values that JUnit will use to test all combinations. |
| **What does assumeTrue do?** | If the condition is false, the test is SKIPPED (not failed). Used to filter invalid inputs. |
| **Why did we need assumptions for negative numbers?** | The theory "a + b > a" only holds for positive numbers. Negative numbers violate it. |
| **What is integer overflow?** | When a calculation exceeds the maximum value an int can hold (2,147,483,647), it wraps around to negative. |
| **Why does commutative property work even with overflow?** | Both a + b and b + a overflow the same way, so they're still equal. |

---

# Q8: Fibonacci & Prime Theories - Rewriting with Theories

## What the Lab Asked

> Using JUnit Theories, rewrite the testing programs you have already provided for Question 5 and 6.

---

## `FibonacciTheoryTest.java`

```java
package test;

import static org.junit.Assert.*;
import static org.junit.Assume.*;
import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;
import main.Fibonacci;

@RunWith(Theories.class)
public class FibonacciTheoryTest {
    
    @DataPoints
    public static int[][] fibonacciData = {
        {0, 0},   // F(0) = 0
        {1, 1},   // F(1) = 1
        {2, 1},   // F(2) = 1
        {3, 2},   // F(3) = 2
        {4, 3},   // F(4) = 3
        {5, 5},   // F(5) = 5
        {6, 8},   // F(6) = 8
        {7, 13},  // F(7) = 13
        {8, 21},  // F(8) = 21
        {9, 34}   // F(9) = 34
    };
    
    @Theory
    public void testFibonacci(int[] data) {
        int index = data[0];
        int expected = data[1];
        
        assumeTrue(index >= 0);
        
        assertEquals(expected, Fibonacci.compute(index));
    }
    
    @DataPoints
    public static int[] indices = {2, 3, 4, 5, 6, 7, 8, 9};
    
    // Property: F(n) = F(n-1) + F(n-2)
    @Theory
    public void testFibonacciProperty(int n) {
        assumeTrue(n >= 2);
        
        int fn = Fibonacci.compute(n);
        int fn1 = Fibonacci.compute(n - 1);
        int fn2 = Fibonacci.compute(n - 2);
        
        assertEquals("F(n) should equal F(n-1) + F(n-2)", fn, fn1 + fn2);
    }
}
```

### Theory: Fibonacci Property

Instead of just checking values, we test the **mathematical property**:

```
F(n) = F(n-1) + F(n-2)

For n = 5:
  F(5) = 5
  F(4) = 3
  F(3) = 2
  
  Is F(5) == F(4) + F(3)?
  Is 5 == 3 + 2?
  Is 5 == 5? YES ✓
```

---

## `PrimeNumberCheckerTheoryTest.java`

```java
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
    
    @DataPoints
    public static Object[][] primeData = {
        {2, true},
        {6, false},
        {19, true},
        {22, false},
        {23, true}
    };
    
    @Theory
    public void testIsPrime(Object[] data) {
        int number = (Integer) data[0];
        boolean expected = (Boolean) data[1];
        
        assumeTrue(number > 0);
        
        assertEquals(expected, PrimeNumberChecker.isPrime(number));
    }
    
    @DataPoints
    public static int[] numbers = {2, 3, 5, 7, 11, 13, 17, 19, 23};
    
    // Theory: All primes > 2 are odd
    @Theory
    public void testPrimesGreaterThan2AreOdd(int n) {
        assumeTrue(n > 2);
        assumeTrue(PrimeNumberChecker.isPrime(n));
        
        assertTrue("Primes > 2 should be odd", n % 2 != 0);
    }
    
    @DataPoints
    public static int[] nonPositives = {-5, -1, 0, 1};
    
    // Theory: Numbers <= 1 are not prime
    @Theory
    public void testNonPositivesNotPrime(int n) {
        assumeTrue(n <= 1);
        
        assertFalse("Numbers <= 1 should not be prime", PrimeNumberChecker.isPrime(n));
    }
}
```

### Theories Tested

1. **Basic verification**: Check known prime/non-prime numbers
2. **Property: Primes > 2 are odd**: All primes except 2 are odd numbers
3. **Property: Numbers ≤ 1 are not prime**: 0, 1, negatives are never prime

---

## Demo Questions for Q8

| Question | Answer |
|----------|--------|
| **Why rewrite with Theories?** | Theories let us test mathematical PROPERTIES, not just specific values. More thorough testing. |
| **What property of Fibonacci did we test?** | F(n) = F(n-1) + F(n-2) - the definition of Fibonacci itself. |
| **What property of primes did we test?** | All primes greater than 2 are odd numbers. |
| **Why cast to Object in prime test?** | DataPoints array contains mixed types (int and boolean). Object[] allows this. |
| **What's the benefit of property-based testing?** | Tests the underlying mathematical truth, not just examples. Catches edge cases you might miss. |

---

# Summary: JUnit Testing Cheat Sheet

## Annotations

| Annotation | Purpose |
|------------|---------|
| `@Test` | Marks method as a test case |
| `@Before` | Runs before EACH test |
| `@After` | Runs after EACH test |
| `@BeforeClass` | Runs ONCE before all tests |
| `@AfterClass` | Runs ONCE after all tests |
| `@Ignore` | Skips this test |
| `@RunWith(...)` | Use custom test runner |
| `@Parameters` | Provides data for parameterized tests |
| `@DataPoints` | Provides data for theories |
| `@Theory` | Marks method as a theory |

## Assertions

| Method | Purpose |
|--------|---------|
| `assertEquals(expected, actual)` | Check values are equal |
| `assertArrayEquals(expected, actual)` | Check arrays are equal |
| `assertTrue(condition)` | Check condition is true |
| `assertFalse(condition)` | Check condition is false |
| `assertNull(value)` | Check value is null |
| `assertNotNull(value)` | Check value is not null |

## Assumptions (for Theories)

| Method | Purpose |
|--------|---------|
| `assumeTrue(condition)` | Skip test if condition is false |
| `assumeFalse(condition)` | Skip test if condition is true |
| `assumeNotNull(value)` | Skip test if value is null |

## Running Tests

```bash
# Compile main classes
javac -d bin -sourcepath src -cp "lib/*" src/main/*.java

# Compile test classes
javac -d bin -sourcepath src -cp "bin;lib/*" src/test/*.java

# Run a specific test
java -cp "bin;lib/*" org.junit.runner.JUnitCore test.ArrayMultTest

# Run multiple tests
java -cp "bin;lib/*" org.junit.runner.JUnitCore test.TriangleTest test.RETest

# Run test suite
java -cp "bin;lib/*" org.junit.runner.JUnitCore test.TestSuite
```

---

# Good Luck on Your Demo! 🎉
