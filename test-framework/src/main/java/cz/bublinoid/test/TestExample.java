package cz.bublinoid.test;

import cz.bublinoid.annotations.AfterSuite;
import cz.bublinoid.annotations.BeforeSuite;
import cz.bublinoid.annotations.Test;

public class TestExample {
    @BeforeSuite
    public void beforeSuite() {
        System.out.println("Before suite");
    }

    @Test(priority = 1)
    public void testMethod1() {
        System.out.println("Test method 1");
    }

    @Test(priority = 3)
    public void testMethod2() {
        System.out.println("Test method 2");
    }

    @Test(priority = 2)
    public void testMethod3() {
        System.out.println("Test method 3");
    }

    @AfterSuite
    public void afterSuite() {
        System.out.println("After suite");
    }
}