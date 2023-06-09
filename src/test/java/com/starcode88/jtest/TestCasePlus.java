package com.starcode88.jtest;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.starcode88.jtest.InitializationError;
import com.starcode88.jtest.TestCase;
import com.starcode88.jtest.TestCaseDescription;
import com.starcode88.jtest.TestCaseDescriptions;
import com.starcode88.jtest.TestExecutionError;
import com.starcode88.jtest.TestRunner;
import com.starcode88.jtest.TextBuilder;

/**
 * For testing our test framework we already can just use it.
 * We will directly use the test case descriptions
 * from the file "testcases.json"
 */
public class TestCasePlus extends TestCase {
	
	private static Logger logger = LogManager.getLogger(TestCasePlus.class);
	
	public static void setUpBeforeClass(String prefix, String baseID) 
				throws InitializationError {
		TestCase.setUpBeforeClass(prefix, baseID);
	}
	
	public void runTest(String id, TestRunner runner) throws TestExecutionError {
		TestCaseDescriptions descs;
		try {
			descs = TestCaseDescriptions.getInstance();
		} catch (IOException | JsonParserException e) {
			throw logger.throwing(new TestExecutionError(TextBuilder.unexpectedException(e), e));
		}

		TestCaseDescription desc = descs.get(id);
		if (desc == null) {
			logger.warn("No description for test case " + id + " found.");
			desc = new TestCaseDescription(
					"?", 
					"No description available because test case with id "
					+ id + " hasn't be found in the test case descriptions.",
					"n/a", 
					"n/a");
		}
		runTest(desc, runner);
	}
}
