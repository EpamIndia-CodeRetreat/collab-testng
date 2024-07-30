package com.epam.framework.api.restassured.Test;

import com.epam.framework.api.httpclient.RestAPIResponse;
import com.epam.framework.api.httpclient.RestAPIResponseValidator;
import com.epam.framework.api.restassured.RestAPIRequest;
import com.epam.framework.api.restassured.RestApIResponse;
import org.apache.http.HttpException;
import org.assertj.core.api.Assertions;
import org.junit.Test;

import java.net.URISyntaxException;

import static com.epam.framework.api.restassured.HttpMethod.*;

public class tests {

    private static String requestBody = "{\n" +
            "  \"title\": \"foo\",\n" +
            "  \"body\": \"bar\",\n" +
            "  \"userId\": \"1\" \n}";
    @Test
    public void
    getTest() throws HttpException, URISyntaxException {
        RestAPIRequest.createRequest("https://dog.ceo/api/").basePath("breeds/image/random");
        RestApIResponse.sendRequest(GET);
        // Write Asset here as people may want to choose different Assert like testng/junit/AssertJ/Hamcrest
        Assertions.assertThat(RestAPIResponseValidator.validateURI("https://dog.ceo/api/breeds/image/random")).isEqualTo(true);
    }

    @Test
    public void postTestWithJsonObjectBody()
    {
        RestAPIRequest.createRequest("https://jsonplaceholder.typicode.com")
                .basePath("/post")
                .body(requestBody);
        RestApIResponse.sendRequest(POST);
        Assertions.assertThat(RestAPIResponse.getResponse().getStatusLine().getStatusCode()).isEqualTo(201);
    }




}

