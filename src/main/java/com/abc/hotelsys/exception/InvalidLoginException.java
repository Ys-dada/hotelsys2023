package com.abc.hotelsys.exception;

/**
 *  无效登录异常
 */
public class InvalidLoginException extends ApplicationException{

    public InvalidLoginException() {
    }

    public InvalidLoginException(String message) {
        super(message);
    }

    public InvalidLoginException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidLoginException(Throwable cause) {
        super(cause);
    }

    public InvalidLoginException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
