package com.starcode88.jtest;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TestCaseDescription {
	
	private static Logger logger = LogManager.getLogger(TestCaseDescription.class);
	
	private String id = "";
	
	private String description = "";

	private boolean emphasize = false;
	
	private List<TestStep> testSteps = null; 
	
	public List<TestStep> getTestSteps() {
		return testSteps;
	}

	public void setTestSteps(List<TestStep> testSteps) {
		this.testSteps = testSteps;
	}

	public TestCaseDescription() {
	}
	
	public TestCaseDescription(String id, String description, List<TestStep> testSteps) {
		this.id = id;
		this.description = description;
		this.testSteps = testSteps;
	}

	public TestCaseDescription(String id, String description, TestStep firstTestStep) {
		this.id = id;
		this.description = description;
		testSteps = new ArrayList<TestStep>();
		testSteps.add(firstTestStep);		
	}

	public TestCaseDescription(String id, String description, String action, String expectedResult) {
		this.id = id;
		this.description = description;
		testSteps = new ArrayList<TestStep>();
		testSteps.add(new TestStep(action, expectedResult));
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
		logger.info("");
		
		if (testSteps.size() == 1) {
			TestStep step = testSteps.get(0);
			logger.info("Action:");
			logParagraph(step.getAction());
			logger.info("");
			logger.info("Expected result:");
			logParagraph(step.getExpectedResult());
			logger.info("");
		} else {
			int count = 1;
			for (TestStep step : testSteps) {
				String text = "" + count + ". Action: ";
				logger.info(text);
				logParagraph(step.getAction());
				logger.info("   Expected result: ");
				logParagraph(step.getExpectedResult());
				logger.info("");
				count ++;
			}
		}		
		logSeparator();
	}
	
	private void logParagraph(String paragraph) {
		String formatted = TextBuilder.formatParagraph(paragraph, "    ", 70);
		String[] lines = formatted.split("\n");
		for (String line : lines) {
			logger.info(line);
		}
	}

	public void logFooter(TestResult result) {
		logSeparator();
		switch (result) {
		case PASSED:
			logger.info("TEST CASE " + this.id + " " + result);
			break;
		case FAILED:
			logger.error("TEST CASE " + this.id + " " + result);
			break;
		case ERROR:
		default:
			logger.fatal("TEST CASE " + this.id + " " + result);
			break;
		}
		logSeparator();
	}
}
