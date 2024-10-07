package com.epam.stepdefinitions;

import com.epam.framework.api.httpclient.HttpMethod;
import com.epam.framework.api.httpclient.RestAPIRequest;
import com.epam.framework.api.httpclient.RestAPIResponse;
import com.epam.framework.api.httpclient.RestAPIResponseValidator;
import com.epam.framework.core.logging.logger.LogLevel;
import com.epam.framework.core.reporting.Reporter;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import junit.framework.Assert;
import org.apache.commons.io.IOUtils;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class SampleAPIStepDef {

    private int returnedStatusCode, obtainedStatusCode;

    @When("I make a POST request to the jsonplaceholder")
    public void iMakeAPOSTRequestToTheJsonplaceholder() throws IOException {
        RestAPIRequest.createRequest("https://jsonplaceholder.typicode.com/posts", HttpMethod.POST);
        RestAPIRequest.addBody("{ \"title\": \"test\",\"body\": \"test\",\"userId\": 1}");
        RestAPIRequest.addHeader("Content-type","application/json; charset=UTF-8");
        RestAPIResponse.sendRequest();
        returnedStatusCode = RestAPIResponse.getStatusCode();
        Reporter.log(LogLevel.INFO, "POST request sent");
    }

    @Then("the resource is created and {int} status code is returned")
    public void theResourceIsCreatedAndStatusCodeIsReturned(int statusCode) {
        Assert.assertEquals(returnedStatusCode, statusCode);
        Reporter.pass("Correct status code is returned by the post request.");
    }

    @When("I make a GET request to the jsonplaceholder")
    public void iMakeAGETRequestToTheJsonplaceholder() throws IOException {
        RestAPIRequest.createRequest("https://jsonplaceholder.typicode.com/posts/1", HttpMethod.GET);
        RestAPIResponse.sendRequest();

        JSONObject responseContent = new JSONObject(RestAPIResponse.getBody());
        responseContent.keySet().stream().map(k -> k +"=>"+ responseContent.get(k)).forEach(System.out::println);
        obtainedStatusCode = RestAPIResponse.getStatusCode();
    }

    @Then("{int} status code is returned")
    public void statusCodeIsReturned(int statusCode) {
        org.testng.Assert.assertEquals(obtainedStatusCode,statusCode);
        Reporter.pass("Get request successful with correct status code");
    }
}
