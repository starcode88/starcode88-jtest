package com.starcode88.jtest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeAll;

public class UT_Base {
	
	private static Logger logger = LogManager.getLogger(UT_Base.class);
	
	@BeforeAll
	public static void setUpBeforeClass() {
		logger.trace(">>> public void setUpBeforeClass()");
		logger.trace("<<< public void setUpBeforeClass()");
	}
}
