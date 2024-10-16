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
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class SampleAPIStepDef {

    private int returnedStatusCode, obtainedStatusCode;
    private String responseTitle="", responseBody="";

    @When("I make a POST request to the jsonplaceholder")
    public void iMakeAPOSTRequestToTheJsonplaceholder() throws IOException {

    }

    @Then("the resource is created and {int} status code is returned")
    public void theResourceIsCreatedAndStatusCodeIsReturned(int statusCode) {
        Assert.assertEquals(returnedStatusCode, statusCode);
        Reporter.pass("Correct status code is returned by the post request.");
    }

    @When("I make a GET request to the jsonplaceholder")
    public void iMakeAGETRequestToTheJsonplaceholder() throws IOException {

    }

    @Then("{int} status code is returned")
    public void statusCodeIsReturned(int statusCode) {
        org.testng.Assert.assertEquals(obtainedStatusCode,statusCode);
        Reporter.pass("Get request successful with correct status code");
    }

    @When("I make a POST request to the jsonplaceholder with {string}")
    public void iMakeAPOSTRequestToTheJsonplaceholderWith(String postBody) throws IOException {
        RestAPIRequest.createRequest("https://jsonplaceholder.typicode.com/posts", HttpMethod.POST);
        RestAPIRequest.addBody(postBody);
        RestAPIRequest.addHeader("Content-type","application/json; charset=UTF-8");
        RestAPIResponse.sendRequest();
        returnedStatusCode = RestAPIResponse.getStatusCode();
        Reporter.log(LogLevel.INFO, "POST request sent");
    }

    @When("^I make a GET request to the jsonplaceholder with ([0-9])$")
    public void iMakeAGETRequestToTheJsonplaceholderWith(int userId) throws IOException {
        RestAPIRequest.createRequest("https://jsonplaceholder.typicode.com/posts/".concat(String.valueOf(userId)), HttpMethod.GET);
        RestAPIResponse.sendRequest();

        JSONObject responseContent = new JSONObject(RestAPIResponse.getBody());
        String val;
        for(String key : responseContent.keySet()){
            if((!key.equals("id")) && (!key.equals("userId"))) {
                val = responseContent.getString(key);
                System.out.println(key + "=>" + val);
                if (key.contentEquals("title"))
                    responseTitle = val;
                if (key.contentEquals("body"))
                    responseBody = val;
            }
        }
        obtainedStatusCode = RestAPIResponse.getStatusCode();
    }

    @Then("the response contains {string} and {string}")
    public void theResponseContainsAnd(String expectedTitle, String expectedBody) {
        org.testng.Assert.assertTrue(responseTitle.contains(expectedTitle));
        org.testng.Assert.assertTrue(responseBody.contains(expectedBody));
        Reporter.pass("The title and body are same as expected");
        Reporter.pass("Title is : " + responseTitle);
        Reporter.pass("Body is : " + responseBody);
    }
}
