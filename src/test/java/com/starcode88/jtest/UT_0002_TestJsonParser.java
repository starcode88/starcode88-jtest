package com.starcode88.jtest;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.io.StringReader;
import java.nio.file.Files;
import java.nio.file.Path;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonValue;
import javax.json.JsonValue.ValueType;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class UT_0002_TestJsonParser extends TestCasePlus {
	
	public static final String BASE_ID = "0002";
	
	private static Logger logger = LogManager.getLogger(UT_0002_TestJsonParser.class);

	public static String getTestClassId() {
		return "UT-" + BASE_ID;
	}
	
	@BeforeAll
	public static void setUpBeforeClass() throws InitializationError {
		setUpBeforeClass("UT", BASE_ID);
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
	
	private void testImpl_02() {
		String string = "{\"elements\": [\"Hello\", null, true, false, 1, 1.5, {\"subelement\": \"xyz\"}]}";
		try {
			JsonObject root = JsonParser.readObject(string);
			JsonArray array = JsonParser.getArray(root, "elements");
			for (JsonValue value : array) {
				logger.info("Processing item in array with name \"elements\"");
				ValueType type = value.getValueType();

				logger.info("Type of current element = " + type.name());
				if (type == ValueType.TRUE) {
					boolean bool = JsonParser.getValueAsBoolean(value);
					assertTrue(bool, "The JSON data type is TRUE, but when getting the value it is not true");
				} else if (type == ValueType.FALSE) {
					boolean bool = JsonParser.getValueAsBoolean(value);
					assertFalse(bool, "The JSON data type is FALSE, but when getting the value it is not false");
				} else if (type == ValueType.NUMBER) {
					int intNumber = JsonParser.getValueAsInt(value);
					// The only two numbers are 1 and 1.5, so the int value is always 1
					assertEquals(1, intNumber, "The int value of the numbers is not 1");
					
					double doubleNumber = JsonParser.getValueAsDouble(value);
					if (doubleNumber != 1 && doubleNumber != 1.5) {
						fail("The double value of the number is not 1 and also not 1.5, but these are the only possible two values in the JSON String");
					}
				} else if (type == ValueType.STRING) {
					String text = JsonParser.getValueAsString(value);
					assertEquals("Hello", text, "The string value is not \"Hello\"");
				} else if (type == ValueType.OBJECT) {
					JsonObject obj = JsonParser.getValueAsObject(value);
					String subelement = JsonParser.getString(obj, "subelement");
					assertEquals("xyz", subelement, "The value of the element with name \"subelement\" is not \"xyz\"");
				}
			}
		} catch (JsonParserException e) {
			fail("Error in processing JSON data: " + e.getMessage());
		}
	}
}
