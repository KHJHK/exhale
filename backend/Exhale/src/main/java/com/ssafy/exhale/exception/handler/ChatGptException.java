package com.ssafy.exhale.exception.handler;

public class ChatGptException extends RuntimeException {
    public ChatGptException() {
        super();
    };

    public ChatGptException(Throwable cause) {
        super(cause);
    }
}
