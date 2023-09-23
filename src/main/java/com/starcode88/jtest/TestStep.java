package com.starcode88.jtest;

public class TestStep {
    private String action = "";
    private String expectedResult = "";
    private String testResult = "";
    private String errorText = "";

    public TestStep() {
    }

    public TestStep(String action, String expectedResult, String testResult, String errorText) {
        this.action = action;
        this.expectedResult = expectedResult;
        this.testResult = testResult;
        this.errorText = errorText;
    }

    public TestStep(String action, String expectedResult) {
        this.action = action;
        this.expectedResult = expectedResult;
        this.testResult = "";
        this.errorText = "";
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
    public String getTestResult() {
        return testResult;
    }
    public void setTestResult(String testResult) {
        this.testResult = testResult;
    }
    public String getErrorText() {
        return errorText;
    }
    public void setErrorText(String errorText) {
        this.errorText = errorText;
    }
}
