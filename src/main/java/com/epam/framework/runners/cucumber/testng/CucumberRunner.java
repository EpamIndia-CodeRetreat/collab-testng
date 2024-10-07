package com.epam.framework.runners.cucumber.testng;

import com.epam.framework.core.Context;
import com.epam.framework.core.TestContext;
import com.epam.framework.core.logging.logger.LogLevel;
import com.epam.framework.core.reporting.Reporter;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import io.cucumber.testng.TestNGCucumberRunner;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Listeners(CucumberTestNGListener.class)
@CucumberOptions(
        features = {"src/test/resources/features/sampleAPITest.feature"},
        glue = {"com.epam.stepdefinitions","com.epam.framework.runners.cucumber"}
//        plugin =  {"pretty", "com.epam.reportportal.cucumber.StepReporter","com.epam.reportportal.cucumber.ScenarioReporter"}
)
public class CucumberRunner extends AbstractTestNGCucumberTests {
    TestContext testContext;
    private TestNGCucumberRunner testNGCucumberRunner;

//    @Before (order = 1)
    public void setUp() {
        testContext = new TestContext();
        testNGCucumberRunner = new TestNGCucumberRunner(this.getClass());
        testContext.getLogger().log(LogLevel.INFO,"<=============================On Before Class======================================>");
        Context scenarioContext = new Context();
        testContext.registerContext(scenarioContext);
        Reporter.register(testContext.getReporter());
        Reporter.setUp();
    }

    @DataProvider
    public Object[][] features() {
        return testNGCucumberRunner.provideScenarios();
    }

    @AfterClass
    public void tearDownClass() {
//        testNGCucumberRunner.finish();
    }
    
}
