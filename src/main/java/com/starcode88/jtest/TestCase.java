package com.starcode88.jtest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TestCase {

	private static Logger logger = LogManager.getLogger(TestCase.class); 
	
	/**
	 * Call this function from your test case in setUpBeforeClass(). If your
	 * implementation of function 'setUpBeforeClass will be empty then you
	 * don't need to call this function here. The method will write log
	 * message.
	 * 
	 * @param logger The logger instance from your test case class
	 * @param classId Prefix of the test plus BASE_ID. 
	 *                it shall be 'TC' for the normal
	 *                test cases (integration tests), 'ST' for tests which
	 *                verify other test cases ('ST' stands for 'self test') and
	 *                'UT' for unit tests which are normal unit tests to check
	 *                our additional classes beside the test cases.
	 * @throws InitializationError
	 */
	public static void setUpBeforeClass(String classId) throws InitializationError {
		logger.info(TextBuilder.SINGLE_LINE);
		logger.info(TextBuilder.prepareForAllTestsInClass(classId));
	}
	
	/**
	 * Call this function here from your setUp method in your class.
	 * @param classId
	 */
	public static void setUp(String classId) {
		logger.info(TextBuilder.SINGLE_LINE);
		logger.info(TextBuilder.prepareForSingleTestInClass(classId));
	}

	/**
	 * Will handle an unexpected exception
	 * @param logger
	 * @param e
	 * @param desc
	 * @throws TestExecutionError
	 */
	private static void handleTestExecutionError(Throwable e,
						TestCaseDescription desc) throws TestExecutionError {
		
		String unexpectedException = TextBuilder.unexpectedException(e); 
		logger.error(unexpectedException, e);
		desc.logFooter(TestResult.ERROR);
		throw new TestExecutionError(unexpectedException, e);
	}
	
	/**
	 * Call this function to run your test. Example: when your test function
	 * in your test case is 'void testImpl()', then you should call this
	 * function here like 'runTest(logger, desc, () -> testImpl())'.
	 * You don't need to handle unexpected exceptions or implement
	 * logging of result.
	 * 
	 * @param logger The logger instance in your test case
	 * @param desc The description of your test case
	 * @param runner Can be used with a lambda expression to run the test
	 *               implementation
	 * @throws TestExecutionError Will be thrown in case of an unexpected
	 *                            exception
	 */
	public static void runTest(TestCaseDescription desc,
						TestRunner runner) throws TestExecutionError {
		try {
			desc.logHeader();
			runner.execute();
			desc.logFooter(TestResult.PASSED);
		} catch (AssertionError e) {
			desc.logFooter(TestResult.FAILED);
			throw e;
		} catch (Throwable e) {
			handleTestExecutionError(e, desc);
		}
	}
}
