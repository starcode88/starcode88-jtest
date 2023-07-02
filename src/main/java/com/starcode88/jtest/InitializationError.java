package com.starcode88.jtest;

/**
 * This exception should be thrown if something did go
 * wrong during the initialization of an test case.
 * Normally, this should be thrown in the
 * setUp() or setUpBeforeClass() method.
 */
public class InitializationError extends Exception {

	private static final long serialVersionUID = 962488575822814039L;

	public InitializationError(String message) {
        super(message);
    }

    public InitializationError(Throwable cause) {
        super(cause); 
    }

    public InitializationError(String message, Throwable cause) {
        super(message, cause);
    }

}
