package com.starcode88.jtest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;

public class UT_0004_SplitString {

	private static Logger logger = LogManager.getLogger(UT_0004_SplitString.class);
	
	@Test
	public void test_01() throws TestExecutionError {
		TestCaseDescription desc = new TestCaseDescription("UT-0004-01", "Split string /hello/world/", "", "");
		TestCaseDescribed.runTest(desc, () -> testImpl_01());
		
	}
	
	public void testImpl_01() {
		String text = "/hello/alice//hello/bob/";
		logger.info("String: {}", text);
		String[] split = text.split("/");
		logger.info("Number of items: {}", split.length);
		for (int i = 0; i < split.length; i++) {
			logger.info("Item[{}] with {} characters: {}", i, split[i].length(), split[i]);
		}
	}
}
