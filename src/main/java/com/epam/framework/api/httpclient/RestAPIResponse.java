package com.epam.framework.api.httpclient;

import com.epam.framework.core.TestContext;
import com.epam.framework.core.logging.logger.LogLevel;
import org.apache.http.HttpResponse;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;


public class RestAPIResponse {
    private static HttpResponse response;
    static RestAPIRequest restAPIRequest;
    public static final TestContext testContext = new TestContext();
    private static int statusCode;
    private static String body;
    private static String bodyEncoded;
    private HttpResponse rawResponse;
    private static byte[] fileEntity;

    private RestAPIResponse() {

    }

    public static HttpResponse getResponse() {
        return response;
    }

    public static HttpResponse sendRequest() throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        if (RestAPIRequest.getHttpClient() == null) {
            testContext.getLogger().log(LogLevel.ERROR, "Please create a valid request by RestAPIRequest.createRequest(Method method)");
            return response;
        }
        RestAPIRequest.createHttpUriRequest();
        RestAPIRequest.getRestAPIRequest().logRequest(RestAPIRequest.getHttpUriRequest());
        response = RestAPIRequest.getHttpClient().execute(RestAPIRequest.getHttpUriRequest());
        RestAPIRequest.getRestAPIRequest().logResponse(response);
        response.getEntity().writeTo(byteArrayOutputStream);
        fileEntity = byteArrayOutputStream.toByteArray();
        bodyEncoded = new String(fileEntity, StandardCharsets.UTF_8);
        body = new String(bodyEncoded.getBytes(StandardCharsets.UTF_8));
        return response;
    }
    public static byte[] getFileEntity() {
        return fileEntity;
    }

    public static String getBody() {
        return body;
    }

    public static String getBodyEncoded() {
        return bodyEncoded;
    }

    public static int getStatusCode() {
        return statusCode;
    }

    HttpResponse getRawResponse() {
        return rawResponse;
    }
}

