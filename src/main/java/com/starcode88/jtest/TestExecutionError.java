package com.starcode88.jtest;

/**
 * This exception should be thrown if something did go
 * wrong during the test execution. Normally, you
 * would to this in the implementation of the test method
 * (typically in the method annotated with @Test)
 */
public class TestExecutionError extends Exception {

    public TestExecutionError(String message) {
        super(message);
    }

    public TestExecutionError(Throwable cause) {
        super(cause); 
    }

    public TestExecutionError(String message, Throwable cause) {
        super(message, cause);
    }

}
