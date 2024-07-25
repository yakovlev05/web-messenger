package ru.yakovlev05.test.webmessenger.exception;

public class CustomException extends RuntimeException{
    public CustomException(String message) {
        super(message);
    }
}
