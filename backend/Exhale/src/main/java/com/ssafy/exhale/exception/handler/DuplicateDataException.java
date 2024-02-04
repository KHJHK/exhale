package com.ssafy.exhale.exception.handler;

public class DuplicateDataException extends RuntimeException {
    public DuplicateDataException() {
        super();
    }

    DuplicateDataException(Throwable cause) {
        super(cause);
    }

    DuplicateDataException(String message, Throwable cause) {
        super(message, cause);
    }
}
