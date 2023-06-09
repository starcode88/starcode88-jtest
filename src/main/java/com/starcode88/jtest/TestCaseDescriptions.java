package com.starcode88.jtest;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import java.io.IOException;
import java.io.StringReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.LinkedHashMap;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonException;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonValue;
import javax.json.JsonValue.ValueType;
import javax.json.stream.JsonParsingException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TestCaseDescriptions {
	
	private static Logger logger = LogManager.getLogger(TestCaseDescriptions.class);

	private static TestCaseDescriptions instance = null;
	
	private LinkedHashMap<String, TestCaseDescription> map = new LinkedHashMap<String, TestCaseDescription>();

	private Path path = null;
	
	public void put(TestCaseDescription desc) {
		if (map.containsKey(desc.getId())) {
			logger.warn("Test case with ID {} already defined, it will be replaced by the new test case", desc.getId());
		}
		String id = desc.getId();
		logger.debug("Add test case with ID \"{}\" to list of test cases", id);
		map.put(id, desc);
	}
	
	public static TestCaseDescriptions getInstance() throws IOException, JsonParserException {
		String filename = System.getProperty("testcases.json");
		if (filename == null) {
			filename = "testcases.json";
		}
		return getInstance(Path.of(filename));
	}
	
	public static TestCaseDescriptions getInstance(Path path) throws IOException, JsonParserException {
		if (instance == null) {
			logger.debug("Global instance of test case descriptions not yet created, we create new instance"); 
			instance = new TestCaseDescriptions();
		}
		if (!(path.equals(instance.path))) {
			instance.load(path);
			instance.path = path;
		}
		return instance;
	}

	public TestCaseDescription get(String key) {
		return map.get(key);
	}
	
	public void clear() {
		logger.debug("Clear all test case descriptions");
		map.clear();
	}
	
	public void load(Path path) throws IOException, JsonParserException {
		try {
			logger.debug("Start reading JSON file {}", path.toString());
			String content;
			content = Files.readString(path);
			JsonObject root = JsonParser.readObject(content);
			load(root);
		} catch (IOException e) {
			logger.error("Reading JSON file {} aborted due to I/O error (IOException)", path.toString());
			throw logger.throwing(e);
		} 		
		logger.debug("Reading JSON file {} finished");
	}
	
	private void load(JsonObject root) throws JsonParserException {
		try {
			JsonArray testcases = JsonParser.getArray(root, "testcases");
			logger.debug("Loop over all elements in the array given by element with name \"testcases\"");
			for (JsonValue testcase : testcases) {
				logger.debug("Get element from array, it is expected that the data type is {}", ValueType.OBJECT);
				JsonObject jsonTestCase = JsonParser.getAsObject(testcase);
				TestCaseDescription desc = new TestCaseDescription();
				JsonParser.copyString(jsonTestCase, "id", (value) -> desc.setId(value));
				JsonParser.copyString(jsonTestCase, "description", (description) -> desc.setDescription(description));
				JsonParser.copyString(jsonTestCase, "action", (action) -> desc.setAction(action));
				JsonParser.copyString(jsonTestCase, "expectedResult", (expectedResult) -> desc.setExpectedResult(expectedResult));
				put(desc);
			}
		} catch (ClassCastException e) {
			logger.error("Error to parse element with name \"testcases\", can't parse it as an array.");
			throw logger.throwing(e);
		} catch (NullPointerException e) {
			logger.error("Error to get element with name \"testcases\"");
			throw logger.throwing(e);
		}
	}
}
