package com.starcode88.jtest;

import static com.starcode88.jtest.Assertions.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class UT_0003_TestCallingBaseClass extends UT_Base {
	
	private static Logger logger = LogManager.getLogger();

	@BeforeAll
	public static void setUpBeforeClass() {
		logger.trace(">>> public void setUpBeforeClass()");
		logger.trace("<<< public void setUpBeforeClass()");
	}

	@Test
	void test() {
	}

}
