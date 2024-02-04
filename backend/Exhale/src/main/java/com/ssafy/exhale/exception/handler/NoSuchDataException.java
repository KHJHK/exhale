package com.ssafy.exhale.exception.handler;

public class NoSuchDataException extends RuntimeException {
    public NoSuchDataException() { }

    public NoSuchDataException(Throwable throwable) {
        super(throwable);
    }
}
