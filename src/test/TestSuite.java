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
