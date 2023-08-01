package com.starcode88.jtest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class UT_0001_TestTextBuilder extends TestCaseDescribed {
	
	public static final String BASE_ID = "0001";
	
	private static Logger logger = LogManager.getLogger(UT_0001_TestTextBuilder.class);

	@BeforeAll
	public static void setUpBeforeClass() throws InitializationError {
		setUpBeforeClass("UT-" + BASE_ID);
	}
	
	@Test
	public void test_01() throws TestExecutionError {
		runTest("UT-" + BASE_ID + "-01", () -> testImpl_01());
	}
	
	private void testImpl_01() {
		String ipsum = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.";
		logger.info("OriginalText: {}", ipsum);
		String formattedString = TextBuilder.formatParagraph(ipsum, "    ", 40);
		logger.info("Ipsum Text:\n{}", formattedString);
	}
}
