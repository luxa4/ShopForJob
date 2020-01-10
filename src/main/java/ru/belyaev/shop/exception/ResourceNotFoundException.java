// Created by Vologda developer.
// Date: 19.11.2019
// Time: 19:20

package ru.belyaev.shop.exception;

import javax.servlet.http.HttpServletResponse;

public class ResourceNotFoundException extends AbstractErrorException {
    private static final long serialVersionUID = 3356722730990341989L;

    public ResourceNotFoundException(String s) {
        super(s, HttpServletResponse.SC_NOT_FOUND);
    }

    public ResourceNotFoundException(String message, Throwable cause) {
        super(message, cause, HttpServletResponse.SC_NOT_FOUND);
    }

    public ResourceNotFoundException(Throwable cause) {
        super(cause, HttpServletResponse.SC_NOT_FOUND);
    }
}
