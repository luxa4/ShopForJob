// Created by Vologda developer.
// Date: 19.11.2019
// Time: 19:17

package ru.belyaev.shop.exception;

public abstract class AbstractErrorException extends IllegalArgumentException {
    private static final long serialVersionUID = 4743106813489511875L;

    private  int code;

    public AbstractErrorException(String s, int code) {
        super(s);
        this.code = code;
    }

    public AbstractErrorException(String message, Throwable cause, int code) {
        super(message, cause);
        this.code = code;
    }

    public AbstractErrorException(Throwable cause, int code) {
        super(cause);
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
