# Lab 1: Understanding JUnit Testing - Plain English Guide

---

# Part 1: What Is This Lab About?

## The Big Picture

Imagine you're building a calculator app. You write code that adds numbers:

```
2 + 3 = 5
```

How do you KNOW it works? You could manually type in numbers and check... but what if you have 100 functions? What if you change something and accidentally break old code?

**Testing = Writing code that checks if your other code works correctly.**

Instead of you manually checking, you write a "test" that does it for you automatically.

---

## What is JUnit?

JUnit is a **tool for Java** that helps you write and run tests.

Think of it like this:

```
Your Code (the thing you built)     →    JUnit Test (the inspector)
       ↓                                        ↓
   "I add numbers"                      "Let me check... 2+3=5? ✓ Correct!"
```

---

## The Basic Idea

```
┌─────────────────────────────────────────────────────────────┐
│                        YOUR CODE                            │
│                                                             │
│    Input: 2, 3                                              │
│    Function: add(2, 3)                                      │
│    Output: 5                                                │
└─────────────────────────────────────────────────────────────┘
                              ↓
┌─────────────────────────────────────────────────────────────┐
│                        YOUR TEST                            │
│                                                             │
│    "I expect add(2, 3) to give me 5"                        │
│    "Did it give me 5?"                                      │
│    "YES → PASS ✓"  or  "NO → FAIL ✗"                        │
└─────────────────────────────────────────────────────────────┘
```

---

# Part 2: The Three Types of Testing in This Lab

## Type 1: Basic JUnit Tests (Q1, Q2, Q3)

**What:** Write individual test methods, each checking one thing.

```
@Test
public void testAddition() {
    assertEquals(5, calculator.add(2, 3));
}
```

**Plain English:** "I expect 5 when I add 2 and 3. Check it."

---

## Type 2: Parameterized Tests (Q5, Q6)

**What:** Run the SAME test with DIFFERENT inputs automatically.

**Without Parameterized (repetitive):**
```
@Test public void testFib0() { assertEquals(0, fib(0)); }
@Test public void testFib1() { assertEquals(1, fib(1)); }
@Test public void testFib2() { assertEquals(1, fib(2)); }
// ... 10 separate methods! Boring!
```

**With Parameterized (smart):**
```
Data: {0,0}, {1,1}, {2,1}, {3,2}, ...

One test method runs 10 times with different data automatically!
```

**Visual:**
```
┌────────────────────────────────────────────┐
│           PARAMETERIZED TEST               │
│                                            │
│   Data: [{0,0}, {1,1}, {2,1}, {3,2}...]   │
│                    ↓                       │
│   Run 1: test with (0, 0) → PASS ✓        │
│   Run 2: test with (1, 1) → PASS ✓        │
│   Run 3: test with (2, 1) → PASS ✓        │
│   ... automatically loops through all!     │
└────────────────────────────────────────────┘
```

---

## Type 3: Theories (Q7, Q8)

**What:** Test a RULE that should ALWAYS be true, not specific values.

**Parameterized:** "Fib(5) should equal 5, Fib(6) should equal 8..."
**Theory:** "For ANY positive a and b, a + b should be greater than a"

**Visual:**
```
┌────────────────────────────────────────────┐
│              THEORY TEST                    │
│                                            │
│   Rule: "a + b > a" (for positive numbers) │
│   Data points: {1, 2, 307, 400567}         │
│                    ↓                       │
│   Test: 1 + 1 > 1?     → 2 > 1 ✓           │
│   Test: 1 + 2 > 1?     → 3 > 1 ✓           │
│   Test: 2 + 307 > 2?   → 309 > 2 ✓         │
│   ... tests ALL COMBINATIONS!              │
└────────────────────────────────────────────┘
```

---

# Part 3: Understanding Each Question

---

## Q1: ArrayMult - Basic Testing

### What You're Building

A function that multiplies two arrays element by element.

### The Problem in Plain English

```
Array 1: [2, 3]
Array 2: [4, 5, 6]

Multiply matching positions:
  Position 0: 2 × 4 = 8
  Position 1: 3 × 5 = 15
  Position 2: No match in Array 1, so just copy 6

Result: [8, 15, 6]
```

### Visual

```
Array 1:    [ 2 ][ 3 ][   ]     (length 2)
               ×    ×    
Array 2:    [ 4 ][ 5 ][ 6 ]     (length 3)
               ↓    ↓    ↓
Result:     [ 8 ][15 ][ 6 ]     (multiply where both exist, copy the rest)
```

### What the Test Does

```
"Give mult() arrays [2,3] and [4,5,6]"
"I expect [8, 15, 6]"
"Did I get [8, 15, 6]? YES → PASS"
```

---

## Q2: Triangle - Fixing Bugs & Testing

### What You're Building

A class that calculates the area of a triangle using Heron's Formula.

### The Problem in Plain English

The original code has a **BUG**. Heron's formula was written wrong.

**Wrong:** `(side - s)`
**Right:** `(s - side)`

That's it. One small mistake = wrong answers.

### Heron's Formula Explained

```
Triangle with sides a, b, c

Step 1: Calculate "s" (half the perimeter)
        s = (a + b + c) / 2

Step 2: Calculate area
        Area = √(s × (s-a) × (s-b) × (s-c))
```

### Visual Example

```
Triangle: sides = 3, 4, 5

Step 1: s = (3 + 4 + 5) / 2 = 6

Step 2: Area = √(6 × (6-3) × (6-4) × (6-5))
             = √(6 × 3 × 2 × 1)
             = √36
             = 6

Area = 6 ✓
```

### What the Tests Check

1. **Area of triangle (3,4,5)** → should be 6
2. **Area of triangle (5,4,3)** → should also be 6 (same triangle, different order)
3. **Area of triangle (8,5,5)** → should be 12
4. **Triangle (3,4,5) and (5,4,3)** → areas should be equal
5. **Valid triangle (3,4,5)** → should be valid
6. **Invalid triangle (3,4,100)** → should be invalid (sides don't make a real triangle)

### What Makes a Triangle Invalid?

```
Valid Triangle Rule: Any two sides added must be bigger than the third.

(3, 4, 5):
  3 + 4 = 7 > 5 ✓
  3 + 5 = 8 > 4 ✓
  4 + 5 = 9 > 3 ✓
  ALL PASS → Valid triangle!

(3, 4, 100):
  3 + 4 = 7 > 100? NO! ✗
  FAILS → Not a real triangle (imagine trying to build it!)
```

---

## Q3: RE (Regular Expressions) - Pattern Matching

### What You're Building

A function that checks if a phone number is in the correct format.

### The Problem in Plain English

You want to accept: `(123) 456 - 7890`
You want to reject: `1234567890` or `abc-def-ghij`

### What is a Regular Expression (Regex)?

A regex is a **pattern** that describes what valid text looks like.

```
Pattern: (XXX) XXX - XXXX
         ↑     ↑     ↑
         3     3     4
       digits digits digits
```

### The Bug in Original Code

In Java, backslash `\` is special. To write `\d` (which means "any digit"), you need `\\d`.

**Wrong:** `"\d{3}"` → Java gets confused
**Right:** `"\\d{3}"` → Java understands "match 3 digits"

### Our Regex Explained

```
\\(\\d{3}\\)\\s*\\d{3}\\s*-\\s*\\d{4}

\\(      →  literal "("
\\d{3}   →  exactly 3 digits
\\)      →  literal ")"
\\s*     →  zero or more spaces
\\d{3}   →  exactly 3 digits
\\s*     →  zero or more spaces
-        →  literal "-"
\\s*     →  zero or more spaces
\\d{4}   →  exactly 4 digits
```

### Visual

```
Input: "(123) 456 - 7890"

Pattern matching:
  \\(     matches "("      ✓
  \\d{3}  matches "123"    ✓
  \\)     matches ")"      ✓
  \\s*    matches " "      ✓
  \\d{3}  matches "456"    ✓
  \\s*    matches " "      ✓
  -       matches "-"      ✓
  \\s*    matches " "      ✓
  \\d{4}  matches "7890"   ✓

ALL MATCH → Valid phone number!
```

---

## Q4: Test Suite - Running Multiple Tests Together

### What You're Building

A single file that runs Q2 tests AND Q3 tests together.

### Plain English

Instead of running tests one file at a time:
```
Run TriangleTest... done
Run RETest... done
```

You run them all at once:
```
Run TestSuite... runs both automatically!
```

### Visual

```
┌─────────────────────────────────────────┐
│              TestSuite                  │
│                                         │
│   ┌─────────────┐  ┌─────────────┐     │
│   │TriangleTest │  │   RETest    │     │
│   │  7 tests    │  │   3 tests   │     │
│   └─────────────┘  └─────────────┘     │
│                                         │
│         Total: 10 tests run!            │
└─────────────────────────────────────────┘
```

---

## Q5: Fibonacci - Parameterized Testing

### What You're Building

A test for the Fibonacci sequence using parameterized testing.

### What is Fibonacci?

Each number = sum of the previous two.

```
Position:  0   1   2   3   4   5   6   7   8   9
Value:     0   1   1   2   3   5   8  13  21  34
                   ↑
              1+1=2  ↑
                  2+3=5
```

### Why Parameterized?

Without it, you'd write 10 separate test methods. Boring!

With it, you define data once and the test runs automatically for each row:

```
Data:
  {0, 0}   →  Fib(0) should equal 0
  {1, 1}   →  Fib(1) should equal 1
  {2, 1}   →  Fib(2) should equal 1
  {3, 2}   →  Fib(3) should equal 2
  ...

One @Test method, runs 10 times!
```

### Visual

```
┌──────────────────────────────────────────────┐
│         PARAMETERIZED TEST FLOW              │
│                                              │
│   @Parameters: [{0,0}, {1,1}, {2,1}, ...]   │
│                        ↓                     │
│   Constructor(0, 0) → Run test → PASS        │
│   Constructor(1, 1) → Run test → PASS        │
│   Constructor(2, 1) → Run test → PASS        │
│   ...                                        │
│   Constructor(9, 34) → Run test → PASS       │
│                                              │
│   Result: 10 tests passed!                   │
└──────────────────────────────────────────────┘
```

---

## Q6: Prime Number Checker - Parameterized Testing

### What You're Building

A function that checks if a number is prime, tested with parameterized tests.

### What is a Prime Number?

A number that can ONLY be divided evenly by 1 and itself.

```
2  → Prime (only 1×2)
3  → Prime (only 1×3)
4  → NOT Prime (2×2)
5  → Prime
6  → NOT Prime (2×3)
...
23 → Prime
```

### The Test Data

Lab says test: `{2, 6, 19, 22, 23}`

```
{2, true}   →  2 is prime
{6, false}  →  6 is NOT prime (divisible by 2 and 3)
{19, true}  →  19 is prime
{22, false} →  22 is NOT prime (divisible by 2 and 11)
{23, true}  →  23 is prime
```

---

## Q7: Math Theory - Testing Mathematical Rules

### What You're Building

Tests for mathematical theories using JUnit Theories.

### What's Different About Theories?

**Parameterized:** "Test specific input → expect specific output"
**Theories:** "Test a RULE that should ALWAYS be true"

### The Mathematical Theory

**For all positive a and b:**
1. `a + b > a` (adding positive b makes it bigger)
2. `a + b > b` (adding positive a makes it bigger)
3. `a + b = b + a` (order doesn't matter - commutative)

### Visual: Why This is True

```
a = 5, b = 3

Theory 1: a + b > a
          5 + 3 > 5
          8 > 5  ✓

Theory 2: a + b > b
          5 + 3 > 3
          8 > 3  ✓

Theory 3: a + b = b + a
          5 + 3 = 3 + 5
          8 = 8  ✓
```

### The 5 Tasks Explained

**Task 1:** Test with `{1, 2, 307, 400567}` - all positive, should work

**Task 2:** Add commutative test (a + b = b + a)

**Task 3:** Try with `{0, -1, -10, -1234, 1, 10, 6789}` - includes negatives!

**Problem:**
```
a = 5, b = -3

a + b > a?
5 + (-3) > 5?
2 > 5?  ✗ FALSE!
```

The theory FAILS for negative numbers because adding a negative makes it SMALLER.

**Task 4:** Use `assumeTrue(a > 0)` to skip negative numbers

```
If a or b is negative → Skip this test (don't fail, just skip)
Only test when both are positive
```

**Task 5:** Try with `Integer.MAX_VALUE` (biggest possible integer)

**Problem: Overflow**
```
Integer.MAX_VALUE = 2,147,483,647

MAX_VALUE + 1 = ???
Expected: 2,147,483,648
Actual: -2,147,483,648 (OVERFLOW! Wraps to negative!)

So: a + b > a becomes FALSE
```

**Solution:** Also skip when sum would overflow.

---

## Q8: Rewrite Q5 & Q6 Using Theories

### What You're Building

Same tests as Q5 and Q6, but using Theory syntax instead of Parameterized.

### Why?

The lab wants you to learn BOTH approaches:
- Parameterized: Constructor + @Parameters
- Theories: @DataPoints + @Theory

### The Difference

**Parameterized (Q5):**
```java
@RunWith(Parameterized.class)
public class FibonacciTest {
    private int index, expected;
    
    public FibonacciTest(int index, int expected) {  // Constructor
        this.index = index;
        this.expected = expected;
    }
    
    @Parameters
    public static Collection<Object[]> data() {...}
    
    @Test
    public void test() {...}
}
```

**Theory (Q8):**
```java
@RunWith(Theories.class)
public class FibonacciTheoryTest {
    @DataPoints
    public static int[][] data = {...};
    
    @Theory
    public void test(int[] data) {...}  // No constructor needed!
}
```

---

# Part 4: Key Concepts Summary

## JUnit Annotations Cheat Sheet

| Annotation | What It Does |
|------------|--------------|
| `@Test` | "This method is a test" |
| `@Before` | "Run this before EACH test" |
| `@RunWith(Parameterized.class)` | "Use parameterized runner" |
| `@Parameters` | "This method provides test data" |
| `@RunWith(Theories.class)` | "Use theories runner" |
| `@DataPoints` | "These are the values to test" |
| `@Theory` | "This is a theory to test" |

## Assertion Methods Cheat Sheet

| Method | What It Checks |
|--------|----------------|
| `assertEquals(expected, actual)` | Are they equal? |
| `assertTrue(condition)` | Is it true? |
| `assertFalse(condition)` | Is it false? |
| `assertArrayEquals(expected, actual)` | Are arrays equal? |

## Assumption Methods (for Theories)

| Method | What It Does |
|--------|--------------|
| `assumeTrue(condition)` | If false, SKIP this test (don't fail) |

---

# Part 5: The Flow of Each Test Type

## Basic Test Flow

```
1. Create object to test
2. Call method with input
3. Check if output matches expected
4. PASS or FAIL
```

## Parameterized Test Flow

```
1. Define test data (list of inputs + expected outputs)
2. JUnit creates test instance for each data row
3. Constructor receives one row of data
4. @Test method runs
5. Repeat for all rows
```

## Theory Test Flow

```
1. Define data points (possible values)
2. JUnit tries ALL COMBINATIONS
3. Check assumptions (skip if false)
4. If assumptions pass, run assertions
5. PASS if all assertions hold for all valid combinations
```

---

# Part 6: Common Demo Questions

| Question | Answer |
|----------|--------|
| What is unit testing? | Testing small pieces of code (like one method) to make sure they work |
| Why use JUnit? | Automates testing - you don't have to manually check everything |
| What does @Test do? | Tells JUnit "this method is a test case" |
| What's the difference between assert and assume? | Assert = if false, test FAILS. Assume = if false, test is SKIPPED |
| Why use parameterized tests? | Avoid writing the same test 10 times with different values |
| Why use theories? | Test mathematical properties that should ALWAYS be true |
| What is the AAA pattern? | Arrange (setup), Act (call method), Assert (check result) |

---

# You've Got This! 🎉

Remember:
1. **Basic tests** = Check specific inputs give specific outputs
2. **Parameterized** = Same test, many different inputs
3. **Theories** = Test rules that should always be true

The code does the checking. You just need to understand WHAT you're checking and WHY.
