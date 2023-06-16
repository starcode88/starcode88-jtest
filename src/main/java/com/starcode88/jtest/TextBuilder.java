package com.starcode88.jtest;

import java.util.Arrays;
import java.util.Iterator;

public class TextBuilder {

	/** Line with length of 78 characters */
	public static final String SINGLE_LINE = "------------------------------------------------------------------------------";
	
	public static final String DOUBLE_LINE = "==============================================================================";
	
	public static final String HASH_LINE   = "##############################################################################";

	/** Produces Text for unexpected exception */
	public static String unexpectedException(Throwable e) {
		return "Unexpected " + e.getClass().getName() + ": " + e.getMessage();
	}
	
	public static String prepareForAllTestsInClass(String classId) {
		return "Prepare test setup for all tests in test class " + classId;
	}
	
	public static String prepareForSingleTestInClass(String classId) {
		return "Prepare test setup for single test in test class " + classId;
	}
	
	public static String formatParagraph(String paragraph, String indentation, int maxLength) {
		StringBuilder outString = new StringBuilder(4096);
		String currentLine = indentation;
		String[] words = paragraph.split("\\s+");
		Iterator<String> iter = Arrays.asList(words).iterator();
		while(iter.hasNext()) {
			String word = iter.next();

			if (currentLine.equals(indentation)) {
				// We always add it if it is just indentation string
				currentLine += word;
			} else {
				if (currentLine.length() + word.length() >= maxLength) {
					// not enough space
					outString.append(currentLine);
					outString.append("\n");
					currentLine = indentation + word;
				} else {
					currentLine += " " + word;
				}
			}
		}
		
		if (!currentLine.equals(indentation)) {
			outString.append(currentLine);
		}
		return outString.toString();
	}
}
