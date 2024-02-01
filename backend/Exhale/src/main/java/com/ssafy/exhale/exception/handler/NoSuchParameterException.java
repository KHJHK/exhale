package com.ssafy.exhale.exception.handler;

public class NoSuchParameterException extends RuntimeException {
    public NoSuchParameterException() { }

    public NoSuchParameterException(Throwable throwable) {
        super(throwable);
    }
}
