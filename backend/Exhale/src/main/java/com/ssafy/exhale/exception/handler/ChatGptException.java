package com.ssafy.exhale.exception.handler;

public class ChatGptException extends RuntimeException {
    public ChatGptException() {};
    public ChatGptException(Throwable throwable) {
        super(throwable);
    }
}
