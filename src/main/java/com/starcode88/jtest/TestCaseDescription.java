package com.starcode88.jtest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TestCaseDescription {
	
	private static Logger logger = LogManager.getLogger(TestCaseDescription.class);
	
	private String id = "";
	
	private String description = "";
	
	private String action = "";
	
	private String expectedResult = "";
	
	private boolean emphasize = false;

	public TestCaseDescription() {
	}
	
	public TestCaseDescription(String id, String description, String action, String expectedResult) {
		this.id = id;
		this.description = description;
		this.action = action;
		this.expectedResult = expectedResult;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getExpectedResult() {
		return expectedResult;
	}

	public void setExpectedResult(String expectedResult) {
		this.expectedResult = expectedResult;
	}

	public TestCaseDescription emphasize() {
		this.emphasize = true;
		return this;
	}
	
	public void logSeparator() {
		if (emphasize) {
			logger.info(TextBuilder.HASH_LINE);
		} else {
			logger.info(TextBuilder.DOUBLE_LINE);
		}
	}
	
	public void logHeader() {
		logSeparator();
		logger.info("TEST CASE ID " + this.id);
		logSeparator();
		logger.info("Description:");
		logParagraph(description);
		logger.info("Action:");
		logParagraph(action);
		logger.info("Expected Result:");
		logParagraph(expectedResult);
		logSeparator();
	}
	
	private void logParagraph(String paragraph) {
		String formatted = TextBuilder.formatParagraph(paragraph, "    ", 70);
		String[] lines = formatted.split("\n");
		for (String line : lines) {
			logger.info(line);
		}
		logger.info("");
	}

	public void logFooter(TestResult result) {
		logSeparator();
		switch (result) {
		case PASSED:
			logger.info("TEST " + this.id + " " + result);
			break;
		case FAILED:
			logger.error("TEST " + this.id + " " + result);
			break;
		case ERROR:
		default:
			logger.fatal("TEST " + this.id + " " + result);
			break;
		}
		logSeparator();
	}
}
