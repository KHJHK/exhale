package com.ssafy.exhale.exception.handler;

public class DuplicateDataException extends RuntimeException {
    public DuplicateDataException() {}

    DuplicateDataException(Throwable throwable) { super(throwable); }
}
