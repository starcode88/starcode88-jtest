package com.starcode88.jtest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.nio.file.Path;

import org.junit.jupiter.api.Test;

import com.starcode88.jtest.TestCaseDescriptions;

public class TestTestCaseDescriptions {
	
	@Test
	public void test1() throws Throwable {
		TestCaseDescriptions descs = new TestCaseDescriptions();
		Path path = Path.of("src/test/resources/testcases1.json");
		descs.load(path);
	}
	
	@Test
	public void test2() {
		Path path1 = Path.of("filename.txt");
		Path path2 = Path.of("filename.txt");
		assertEquals(path1, path2);
	}
}
