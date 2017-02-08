package com.example.exceptions;

/**
 * Created by huii on 17/1/5.
 */
public class DefaultErrorBundle implements ErrorBundle {

    private static final String DEFAULT_ERROR_MSG = "Unknown error";

    private final Exception exception;

    public DefaultErrorBundle(Exception exception) {
        this.exception = exception;
    }

    @Override
    public Exception getException() {
        return exception;
    }

    @Override
    public String getErroMessage() {
        return DEFAULT_ERROR_MSG;
    }
}
