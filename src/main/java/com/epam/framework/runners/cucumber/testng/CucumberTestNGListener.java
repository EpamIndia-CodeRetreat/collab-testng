package com.epam.framework.runners.cucumber.testng;

import com.epam.framework.core.reporting.Reporter;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.util.HashMap;
import java.util.Map;

public class CucumberTestNGListener implements ITestListener {

    @Override
    public synchronized void onStart(ITestContext context) {
        /*Map<String,String> testNgParameters = context.getCurrentXmlTest().getAllParameters();
        System.setProperty("cucumber.execution.strict","true");
        System.setProperty("cucumber.features",testNgParameters.get("features"));
        String glue = testNgParameters.get("glue1") +"," + testNgParameters.get("glue2");
        System.setProperty("cucumber.glue", glue);
        if(!testNgParameters.get("tags").equals("${tags}")){
            System.setProperty("cucumber.filter.tags", testNgParameters.get("tags"));
        }*/
    }
    @Override
    public synchronized void onFinish(ITestContext context) {
        Reporter.tearDown();
    }
    @Override
    public synchronized void onTestStart(ITestResult result) {
        String methodName = result.getMethod().getMethodName();
        Map<String, Object> additionalParams = new HashMap<>();
        additionalParams.put("ITestResult", result);
        additionalParams.put("desc", result.getMethod().getDescription());
        Reporter.createTest(methodName, additionalParams);
        Reporter.info("TEST EXECUTION STARTED - "+methodName);
    }
    @Override
    public synchronized void onTestSuccess(ITestResult result) {
        Reporter.pass("PASSED - "+result.getName());
    }
    @Override
    public synchronized void onTestFailure(ITestResult result) {
        String methodName = result.getMethod().getMethodName();
        Reporter.fail("FAILED - " + result.getName(), result.getThrowable());
        Reporter.addScreenshot(methodName + "-Failed", "Screenshot After Test Failure..!!!");
    }
    @Override
    public synchronized void onTestSkipped(ITestResult result) {
        Reporter.skip("SKIPPED - "+result.getName(), result.getThrowable());
    }
}
