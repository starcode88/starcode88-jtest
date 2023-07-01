package com.starcode88.jtest;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.function.Executable;
import org.opentest4j.AssertionFailedError;

public class Assertions {

	private static Logger logger = LogManager.getLogger(Assertions.class);
	
	private static Level assertionFailedLevel = Level.getLevel("ASSERTION_FAILED");
	
	public static void setLogLevel(String level) {
		assertionFailedLevel = Level.getLevel(level);
	}
	
	public static void assertArrayEquals(byte[] expected, byte[] actual) {
		try {
			org.junit.jupiter.api.Assertions.assertArrayEquals(expected, actual);
		} catch (AssertionFailedError e) {
			throw logger.throwing(assertionFailedLevel, e);
		}
	}

	public static void assertArrayEquals(byte[] expected, byte[] actual, String message) {
		try {
			org.junit.jupiter.api.Assertions.assertArrayEquals(expected, actual, message);
		} catch (AssertionFailedError e) {
			throw logger.throwing(assertionFailedLevel, e);
		}
	}

	public static void assertEquals(int expected, int actual) {
		try {
			org.junit.jupiter.api.Assertions.assertEquals(expected, actual);
		} catch (AssertionFailedError e) {
			throw logger.throwing(assertionFailedLevel, e);
		}
	}
	
	public static void assertEquals(int expected, int actual, String message) {
		try {
			org.junit.jupiter.api.Assertions.assertEquals(expected, actual, message);
		} catch (AssertionFailedError e) {
			throw logger.throwing(assertionFailedLevel, e);
		}
	}
	
	public static void assertEquals(String expected, String actual) {
		try {
			org.junit.jupiter.api.Assertions.assertEquals(expected, actual);
		} catch (AssertionError e) {
			throw logger.throwing(assertionFailedLevel, e);
		}
	}

	public static void assertEquals(String expected, String actual, String message) {
		try {
			org.junit.jupiter.api.Assertions.assertEquals(expected, actual, message);
		} catch (AssertionError e) {
			throw logger.throwing(assertionFailedLevel, e);
		}
	}

	public static void assertFalse(boolean condition) {
		try {
			org.junit.jupiter.api.Assertions.assertFalse(condition);
		}  catch (AssertionFailedError e) {
			throw logger.throwing(assertionFailedLevel, e);
		}
	}
	
	public static void assertNotNull(Object obj) {
		try {
			org.junit.jupiter.api.Assertions.assertNotNull(obj);
		} catch (AssertionError e) {
			throw logger.throwing(assertionFailedLevel, e);
		}
	}

	public static void assertNotNull(Object obj, String message) {
		try {
			org.junit.jupiter.api.Assertions.assertNotNull(obj, message);
		} catch (AssertionError e) {
			throw logger.throwing(assertionFailedLevel, e);
		}
	}

	public static void assertNull(Object obj) {
		try {
			org.junit.jupiter.api.Assertions.assertNull(obj);
		} catch (AssertionError e) {
			throw logger.throwing(assertionFailedLevel, e);
		}
	}

	public static void assertNull(Object obj, String message) {
		try {
			org.junit.jupiter.api.Assertions.assertNull(obj, message);
		} catch (AssertionError e) {
			throw logger.throwing(assertionFailedLevel, e);
		}
	}
	
	public static <T extends Throwable> T assertThrows(Class<T> expectedType, Executable executable) {
		try {
			return org.junit.jupiter.api.Assertions.assertThrows(expectedType, executable);
		} catch (AssertionFailedError e) {
			throw logger.throwing(assertionFailedLevel, e);
		}
	}
		
	public static <T extends Throwable> T assertThrows(Class<T> expectedType, Executable executable, String message) {
		try {
			return org.junit.jupiter.api.Assertions.assertThrows(expectedType, executable, message);
		} catch (AssertionFailedError e) {
			throw logger.throwing(assertionFailedLevel, e);
		}
	}

	public static void assertTrue(boolean condition) {
		try {
			org.junit.jupiter.api.Assertions.assertTrue(condition);
		}  catch (AssertionFailedError e) {
			throw logger.throwing(assertionFailedLevel, e);
		}
	}
	
	public static void assertTrue(boolean condition, String message) {
		try {
			org.junit.jupiter.api.Assertions.assertTrue(condition, message);
		}  catch (AssertionFailedError e) {
			throw logger.throwing(assertionFailedLevel, e);
		}
	}
	
	public static <V> V fail(String message) {
		try {
			return org.junit.jupiter.api.Assertions.fail(message);
		}  catch (AssertionFailedError e) {
			throw logger.throwing(assertionFailedLevel, e);
		}
	}
	
	public static <V> V fail(Throwable cause) {
		try {
			return org.junit.jupiter.api.Assertions.fail(cause);
		} catch (AssertionFailedError e) {
			throw logger.throwing(assertionFailedLevel, e);
		}
	}
	
	public static <V> V fail(String message, Throwable cause) {
		try {
			return org.junit.jupiter.api.Assertions.fail(message, cause);
		} catch (AssertionFailedError e) {
			throw logger.throwing(assertionFailedLevel, e);
		}
	}
}
