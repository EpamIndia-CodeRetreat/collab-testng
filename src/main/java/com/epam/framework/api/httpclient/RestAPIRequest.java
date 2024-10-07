package com.epam.framework.api.httpclient;

import com.epam.framework.core.TestContext;
import com.epam.framework.core.exceptions.TAFRuntimeException;
import com.epam.framework.core.logging.logger.LogLevel;
import com.epam.framework.core.reporting.Reporter;
import io.vavr.control.Try;
import org.apache.commons.codec.binary.Base64;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.*;
import org.apache.http.cookie.Cookie;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static java.lang.String.format;

public class RestAPIRequest {

    public static final TestContext testContext = new TestContext();
    private static HttpClient httpClient = null;
    private static HttpUriRequest request = null;
    private static RequestBuilder requestBuilder = null;
    private String succeededLog;
    private boolean withAuth;
    private String login;
    private String password;
    private String requestLog;
    private boolean logResponseBody = true;
    private String responseLog;
    private static RestAPIRequest restAPIRequest = new RestAPIRequest();

    public static RestAPIRequest getRestAPIRequest() {
        return restAPIRequest;
    }

    public static RequestBuilder getRequestBuilder() {
        return requestBuilder;
    }

    public static HttpUriRequest getHttpUriRequest() {
        return request;
    }

    public static HttpClient getHttpClient() {
        return httpClient;
    }

    private RestAPIRequest() {
    }

    public static HttpClient getHttpClient(String path) {
        httpClient = HttpClients.custom().build();
        return httpClient;
    }

    public static void createHttpUriRequest() {
        request = getRequestBuilder().build();
    }

    public static void addBody(String body) {
        requestBuilder = getRequestBuilder().setEntity(new StringEntity(body, ContentType.APPLICATION_JSON));

    }

    public static void addBodyAsFormData(final Map<String, String> formData) {
        StringBuilder formBuilder = new StringBuilder();
        for (Map.Entry<String, String> formEntry : formData.entrySet()) {
            Try.run(() -> {
                formBuilder.append(URLEncoder.encode(formEntry.getKey(), StandardCharsets.UTF_8.name()));
                formBuilder.append("=");
                formBuilder.append(URLEncoder.encode(formEntry.getValue(), StandardCharsets.UTF_8.name()));
                formBuilder.append("&");
            }).getOrElseThrow(exception -> {
                testContext.getLogger().log(LogLevel.ERROR, exception);
                throw new TAFRuntimeException("constructing groovy failed", exception);
            });
        }
        formBuilder.deleteCharAt(formBuilder.length() - 1);
        addBody(formBuilder.toString());
    }

    public static void addHeader(final String key, final String value) {
        requestBuilder = getRequestBuilder().addHeader(key, value);
    }

    public static RequestBuilder createRequest(String path, HttpMethod requestType) {
        httpClient = getHttpClient(path);

        HttpClient client = HttpClients.custom().build();
        switch (requestType) {
            case GET:
                requestBuilder = RequestBuilder.get()
                        .setUri(path);
                break;
            case POST:
                requestBuilder = RequestBuilder.post()
                        .setUri(path);
                break;
            case DELETE:
                requestBuilder = RequestBuilder.delete()
                        .setUri(path);

                break;
            case PUT:
                requestBuilder = RequestBuilder.put()
                        .setUri(path);

                break;
            case PATCH:
                requestBuilder = RequestBuilder.patch()
                        .setUri(path);

                break;
            case OPTIONS:
                requestBuilder = RequestBuilder.options()
                        .setUri(path);

                break;
            default:
                testContext.getLogger().log(LogLevel.INFO, "Please give the correct action like GET,PUT,POST :");
                break;

        }
        return requestBuilder;
    }
    public RestAPIRequest addBasicAuth(final String login, final String password) {
        withAuth = true;
        this.login = login;
        this.password = password;
        String encodedAuthorization = "Basic " + Base64.encodeBase64String((login + ":" + password).getBytes());
        addHeader("Authorization", encodedAuthorization);
        return this;
    }
    public static void addContentType(final String value) {
        requestBuilder = getRequestBuilder().addHeader("Content-Type", value);
    }

    public static void addBearerTokenAuth(final String accessToken) {
        requestBuilder = getRequestBuilder().addHeader("Authorization", format("Bearer %s", accessToken));
    }

    private static boolean isBodyApplicableTo(final HttpUriRequest request) {
        return (request.getClass().equals(HttpPut.class) || request.getClass().equals(HttpPatch.class)
                || request.getClass().equals(HttpPost.class) || request.getClass().equals(HttpDeleteWithBody.class));
    }

    public void logRequest(final HttpUriRequest rawRequest) throws IOException {
        StringBuilder requestDescription = new StringBuilder("=== REQUEST ===\n");
        succeededLog = rawRequest.getRequestLine().toString();
        requestDescription.append(rawRequest.getRequestLine().toString()).append("\n");
        for (Header header : rawRequest.getAllHeaders()) {
            requestDescription.append(header).append("\n");
        }
        requestDescription.append("COOKIES:").append("\n");
        for (Cookie cookie : SecureClientInitializer.getDefaultSecureHttpClient().getCookies()) {
            requestDescription.append(cookie).append("\n");
        }

        if (withAuth) {
            requestDescription.append("User/password: ").append(login).append("/").append(password).append("\n");
        }

        if (isBodyApplicableTo(rawRequest)) {
            HttpEntity entity = ((HttpEntityEnclosingRequestBase) rawRequest).getEntity();
            if (Objects.nonNull(entity)) {
                requestDescription.append(EntityUtils.toString(entity));
            }
        }
        requestDescription.append("\n");
        requestLog = requestDescription.toString();
    }

    public void logResponse(final HttpResponse response) throws IOException {
        StringBuilder responseDescription = new StringBuilder("=== RESPONSE ===\n");
        succeededLog = format("%s completed with code %s", succeededLog,
                response.getStatusLine().toString());
        responseDescription.append(response.getStatusLine().toString()).append("\n");
        for (Header header : response.getAllHeaders()) {
            responseDescription.append(header).append("\n");
        }

        if (logResponseBody) {
            String responseBody = EntityUtils.toString(RestAPIResponse.getResponse().getEntity(), StandardCharsets.UTF_8);
            responseDescription.append(responseBody).append("\n");
        } else {
            responseDescription.append("-skip-body-\n");
        }

        List<Cookie> cookies = SecureClientInitializer.getDefaultSecureHttpClient().getCookies();
        if (!cookies.isEmpty()) {
            responseDescription.append("Cookies:\n");
            for (Cookie cookie : cookies) {
                responseDescription.append("Cookie: ").append(cookie).append("\n");
            }
        }
        responseLog = responseDescription.toString();
    }

    private void printLogs() {
        testContext.getLogger().log(LogLevel.INFO, "================================ REQUEST LOG ================================");
        testContext.getLogger().log(LogLevel.INFO, requestLog);
        testContext.getLogger().log(LogLevel.INFO, "================================ REQUEST LOG END ================================");
        testContext.getLogger().log(LogLevel.INFO, "================================ RESPONSE LOG ================================");
        testContext.getLogger().log(LogLevel.INFO, responseLog);
        testContext.getLogger().log(LogLevel.INFO, "================================ RESPONSE LOG END ================================");

        Reporter.log(LogLevel.INFO, "================================ REQUEST LOG ================================");
        Reporter.addFile("RequestLog.json", "Request Sent", requestLog);
        Reporter.log(LogLevel.INFO, "================================ RESPONSE LOG ================================");
        Reporter.addFile("ResponseLog.json", "Response Received", responseLog);


    }

}