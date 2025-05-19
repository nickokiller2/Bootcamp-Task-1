package testng;

import org.testng.annotations.Test;

public class simpletest2 {
    @Test
    public void testMethod2() {
        System.out.println("This is a simple test method 2.");
    }
    @Test (dependsOnMethods = "testMethod2")
    public void testMethod3() {
        System.out.println("This is a simple test method 3.");
    }
}
