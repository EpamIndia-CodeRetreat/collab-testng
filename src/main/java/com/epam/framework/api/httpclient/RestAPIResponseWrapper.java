package com.epam.framework.api.httpclient;


import com.epam.framework.core.TestContext;
import com.epam.framework.core.exceptions.CollabFrameworkRuntimeException;

import com.epam.framework.core.logging.logger.LogLevel;
import org.apache.http.HttpResponse;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;


public class RestAPIResponseWrapper {

    public static TestContext testContext = new TestContext();
    private final int statusCode;
    private String body;
    private String bodyEncoded;
    private HttpResponse rawResponse;
    private byte[] fileEntity;

    public RestAPIResponseWrapper(final HttpResponse httpResponse) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        rawResponse = httpResponse;
        statusCode = httpResponse.getStatusLine().getStatusCode();
        if (statusCode == 204) {
            bodyEncoded = null;
        } else {
            try {
                httpResponse.getEntity().writeTo(byteArrayOutputStream);
                fileEntity = byteArrayOutputStream.toByteArray();
                bodyEncoded = new String(fileEntity, StandardCharsets.UTF_8);
                body = new String(bodyEncoded.getBytes(StandardCharsets.UTF_8));
            } catch (IOException e) {
                testContext.getLogger().log(LogLevel.ERROR,e.getMessage());
                throw new CollabFrameworkRuntimeException(e.getMessage());
            }
        }
    }

    public byte[] getFileEntity() {
        return fileEntity;
    }

    public String getBody() {
        return body;
    }

    public String getBodyEncoded() {
        return bodyEncoded;
    }

    public int getStatusCode() {
        return statusCode;
    }

    HttpResponse getRawResponse() {
        return rawResponse;
    }
}
