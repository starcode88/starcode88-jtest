package com.starcode88.jtest;

public class JsonParserException extends Exception {

	private static final long serialVersionUID = 800452785765466136L; 

	public JsonParserException(String message) {
		super(message);
	}
	
	public JsonParserException(Throwable e) {
		super(e);
	}
	
	public JsonParserException(String message, Throwable e) {
		super(message, e);
	}
}
