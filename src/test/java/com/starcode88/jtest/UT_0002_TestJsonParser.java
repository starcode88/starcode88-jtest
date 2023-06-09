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
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class UT_0002_TestJsonParser {
	
	public static final String BASE_ID = "0002";
	
	private static Logger logger = LogManager.getLogger(UT_0002_TestJsonParser.class);

	/**
	 * root.get("x") returns null if it does not exist
	 * @throws Throwable
	 */
	
	@Test
	@DisplayName("UT-" + BASE_ID + "-01")
	void test01() throws Throwable {
		try {
			String string = "{\"testcases\": []}";
			JsonObject obj = JsonParser.readObject(string);
			JsonArray testcases = JsonParser.getArray(obj, "testcases"); 
		} catch (Throwable e) {
			throw logger.throwing(e);
		}
	}

	private String readFile(String filename) throws IOException {
		return Files.readString(Path.of(filename));
	}
}
