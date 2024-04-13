package com.project.memo.exception;

public class NoSuchMemoException extends RuntimeException{

    public NoSuchMemoException() {
        super();
    }

    public NoSuchMemoException(String message) {
        super(message);
    }

    public NoSuchMemoException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoSuchMemoException(Throwable cause) {
        super(cause);
    }

    protected NoSuchMemoException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
