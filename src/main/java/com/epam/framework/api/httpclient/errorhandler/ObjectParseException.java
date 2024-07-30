package com.epam.framework.api.httpclient.errorhandler;

public class ObjectParseException extends RuntimeException{
    private String message;

    public ObjectParseException(String message) {
        super(message);
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}