package com.starcode88.jtest;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fasterxml.jackson.core.exc.StreamWriteException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * For testing our test framework we already can just use it.
 * We will directly use the test case descriptions
 * from the file "testcases.json"
 */
public class TestCaseDescribed extends TestCase {
	
	private static Logger logger = LogManager.getLogger(TestCaseDescribed.class);
	
	private static TestCaseDescriptions descs = null;
	
	public static void setUpBeforeClass(String classId) 
				throws InitializationError {
		TestCase.setUpBeforeClass(classId);
		if (descs == null) {
			try {
				ObjectMapper mapper = new ObjectMapper();
				descs = mapper.readValue(new File("testcases.json"), TestCaseDescriptions.class);
				descs.buildIndex();
			} catch (IOException e) {
				throw logger.throwing(new InitializationError(TextBuilder.unexpectedException(e), e));
			}
		}
	}

	public static void tearDownAfterClass() {
		/*
		if (descs != null) {
			ObjectMapper mapper = new ObjectMapper();
			try {
				mapper.writerWithDefaultPrettyPrinter().writeValue(new File("testresults.json"), descs);
			} catch (IOException e) {
				logger.fatal("Failed to write test results to file \"testresults.json\"", e);
			}
		}*/
	}
	
	public static void runTest(String id, TestRunner runner) throws TestExecutionError {
		if (id == null) throw logger.throwing(new IllegalArgumentException("The argument <id> is null."));
		if (runner == null) throw logger.throwing(new IllegalArgumentException("The argument <runner> is null."));

		if (descs == null) {
			throw logger.throwing(new TestExecutionError("The list of test case descriptions is null. It should have been loaded already in method setUpBeforeClass. Maybe we forgot to call the method setUpBeforeClass before running the test implementation"));
		}
		
		TestCaseDescription desc = descs.get(id);
		if (desc == null) {
			logger.warn("No description for test case " + id + " found.");
			ArrayList<TestStep> testSteps = new ArrayList<TestStep>();
			TestStep testStep = new TestStep();
			testSteps.add(testStep);
			desc = new TestCaseDescription(
					id, 
					"No description available because test case with id "
					+ id + " hasn't be found in the test case descriptions.",
					testSteps);
		}
		runTest(desc, runner);
	}
}
