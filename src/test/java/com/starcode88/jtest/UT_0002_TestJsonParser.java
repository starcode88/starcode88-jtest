package com.starcode88.jtest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

class UT_0002_TestJsonParser extends TestCaseDescribed {
	
	public static final String BASE_ID = "0002";
	
	private static Logger logger = LogManager.getLogger(UT_0002_TestJsonParser.class);

	public static String getTestClassId() {
		return "UT-" + BASE_ID;
	}
	
	@BeforeAll
	public static void setUpBeforeClass() throws InitializationError {
		setUpBeforeClass(getTestClassId());
	}
	
	@Test
	@DisplayName("UT-" + BASE_ID + "-01")
	public void test_01() throws TestExecutionError {
		runTest(getTestClassId() + "-01", () -> testImpl_01());
	}
	
	private void testImpl_01() {
		logger.info("Just print a log message");
	}
	
	@Test
	@DisplayName("UT-" + BASE_ID + "-02")
	public void test_02() throws TestExecutionError {
		runTest(getTestClassId() + "-02", () -> testImpl_02());
	}

	private void testImpl_02() throws TestExecutionError {
		String string = "{\"elements\": [\"Hello\", null, true, false, 1, 1.5, {\"subelement\": \"xyz\"}]}";
		ObjectMapper mapper = new ObjectMapper();
		try {
			JsonNode node = mapper.readTree(string);
		} catch (JsonProcessingException e) {
			throw logger.throwing(new TestExecutionError(TextBuilder.unexpectedException(e), e));
		}
	}
}
