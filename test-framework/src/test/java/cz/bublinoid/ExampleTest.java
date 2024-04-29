package cz.bublinoid;

import cz.bublinoid.annotations.AfterSuite;
import cz.bublinoid.annotations.BeforeSuite;
import cz.bublinoid.annotations.Test;

public class ExampleTest {

    @BeforeSuite
    public void beforeSuite() {
        System.out.println("BeforeSuite method");
    }

    @Test(priority = 5)
    public void testMethod1() {
        System.out.println("Test method 1");
    }

    @Test(priority = 10)
    public void testMethod2() {
        System.out.println("Test method 2");
    }

    @Test(priority = 1)
    public void testMethod3() {
        System.out.println("Test method 3");
        throw new RuntimeException("Test failed");
    }

    @AfterSuite
    public void afterSuite() {
        System.out.println("AfterSuite method");
    }
}