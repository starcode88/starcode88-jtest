package com.starcode88.jtest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.StringReader;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonException;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonString;
import javax.json.JsonStructure;
import javax.json.JsonValue;
import javax.json.JsonValue.ValueType;
import javax.json.stream.JsonParsingException;

public class JsonParser {
	
	private static Logger logger = LogManager.getLogger(JsonParser.class);
	
	public static JsonObject readObject(String string) throws JsonParserException {
		logger.debug("Reading JSON string: {}", string);
		JsonStructure structure = read(string);
		ValueType type = structure.getValueType();
		logger.debug("Type of JSON string = {}", type.name());
		if (type == ValueType.OBJECT) {
			logger.debug("Reading JSON string finished.");
			return structure.asJsonObject();
		}
		throw logger.throwing(new JsonParserException("The JSON string doesn't represent a " + ValueType.OBJECT + ", instead it is of type " + type.name())); 
	}
	
	public static JsonObject getAsObject(JsonValue value) throws JsonParserException {
		ValueType type = value.getValueType();
		if (type != ValueType.OBJECT) {
			throw logger.throwing(new JsonParserException("The current element has the wrong data type. It was expected that it is of type OBJECT, but instead it is of type " + type.name()));
		}
		logger.debug("The current element has the correct data type of type " + ValueType.OBJECT);
		return (JsonObject) value;
	}
	
	public static JsonObject getObject(JsonObject obj, String key) throws JsonParserException {
		JsonValue value = getValueChecked(obj, key, ValueType.OBJECT);
		return (JsonObject) value;
	}
	
	public static JsonArray getArray(JsonObject obj, String key) throws JsonParserException {
		JsonValue value = getValueChecked(obj, key, ValueType.ARRAY);
		return (JsonArray) value;
	}
	
	public static String getString(JsonObject obj, String key) throws JsonParserException {
		JsonValue value = getValueChecked(obj, key, ValueType.STRING);
		JsonString jsonString = (JsonString) value;
		return jsonString.getString();
	}
	
	public static String copyString(JsonObject obj, String key, StringSetter setter) throws JsonParserException {
		String value = getString(obj, key);
		setter.set(value);
		return value;
	}
	
	/**
	 * After calling this function the calling method can be sure
	 * that the returned value can be safely casted to the data
	 * type which was given as argument "type".
	 * @param obj
	 * @param key
	 * @param type
	 * @return
	 * @throws JsonParserException
	 */
	private static JsonValue getValueChecked(JsonObject obj, String key, ValueType type) throws JsonParserException {
		checkKeyExists(obj, key);
		JsonValue value = obj.get(key);
		checkValueNotNull(key, value);
		checkValueType(key, value, type);
		return value;
	}

	private static JsonStructure read(String string) throws JsonParserException {
		try {
			JsonReader reader = Json.createReader(new StringReader(string));
			JsonStructure structure = reader.read();
			return structure;
		} catch (JsonParsingException e) {
			throw logger.throwing(new JsonParserException("Failed to parse JSON string"));
		}
	}
	
	private static void checkKeyExists(JsonObject obj, String key) throws JsonParserException {
		if (!obj.containsKey(key)) {
			throw logger.throwing(new JsonParserException("Couldn't find a element with name " + key));
		}
		logger.debug("Element with name \"{}\" exists", key);
	}
	
	private static void checkValueType(String key, JsonValue value, ValueType expected) throws JsonParserException {
		ValueType type = value.getValueType();
		if (type != expected) {
			throw logger.throwing(new JsonParserException("The data type of element with name \"" + key + "\" is wrong. It was expected that it is of type " + expected.name() + ", but instead it was of type " + type.name())); 
		}
		logger.debug("The data type of element with name \"{}\" is of type {} as expected", key, expected.name());
	}
	
	private static void checkValueNotNull(String key, JsonValue value) throws JsonParserException {
		if (value == null) {
			throw logger.throwing(new JsonParserException("The value of element with name \"" + key + "\" is null"));
		}
		logger.debug("The value of element with name \"" + key + "\" is not null, this is as expected");
	}
}