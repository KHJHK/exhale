package com.ssafy.exhale.exception.handler;

public class InValidParameterLogicException extends RuntimeException {
    public InValidParameterLogicException() {
        super();
    }

    public InValidParameterLogicException(String message) {
        super(message);
    }

    public InValidParameterLogicException(String message, Throwable cause) {
        super(message, cause);
    }

    public InValidParameterLogicException(Throwable cause) {
        super(cause);
    }
}
