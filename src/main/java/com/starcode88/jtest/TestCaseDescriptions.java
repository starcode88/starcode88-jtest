package com.starcode88.jtest;

import java.util.LinkedHashMap;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class TestCaseDescriptions {
	
	@JsonIgnore
	private static Logger logger =
					LogManager.getLogger(TestCaseDescriptions.class);

	@JsonIgnore
	private LinkedHashMap<String, TestCaseDescription>  
					map = new LinkedHashMap<String, TestCaseDescription>();

	private List<TestCaseDescription> testcases = null;
	
	public List<TestCaseDescription> getTestcases() {
		return testcases;
	}

	public void setTestcases(List<TestCaseDescription> testcases) {
		this.testcases = testcases;
	}
	
	/**
	 * Puts the test case description into the map. It has been made
	 * private to avoid building inconsitency between the list of test cases
	 * and map.
	 * @param desc The TestCaseDescription which will be put into the map
	 */
	private void putIntoMap(TestCaseDescription desc) {
		String id = desc.getId();
		if (map.containsKey(id)) {
			logger.warn("Test case with ID {} already defined, it will be replaced by the new test case", desc.getId());
		}
		map.put(id, desc);
	}
	
	/**
	 * Builds the index, that means it will add all test case descriptions
	 * into the map where the id will be used as the key. 
	 */
	public void buildIndex() {
		map.clear();
		for (TestCaseDescription desc : testcases) {
			putIntoMap(desc);
		}
	}
	
	/*
		String filename = System.getProperty("testcases.json");
		if (filename == null) {
			filename = "testcases.json";
		}
		return getInstance(Path.of(filename));
	*/
	
	public TestCaseDescription get(String key) {
		return map.get(key);
	}
}
