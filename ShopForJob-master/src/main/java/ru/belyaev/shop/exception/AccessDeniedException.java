// Created by Vologda developer.
// Date: 19.11.2019
// Time: 19:19

package ru.belyaev.shop.exception;

import javax.servlet.http.HttpServletResponse;

public class AccessDeniedException extends AbstractErrorException {
    private static final long serialVersionUID = 3582095290018138160L;

    public AccessDeniedException(String s) {
        super(s, HttpServletResponse.SC_FORBIDDEN);
    }

    public AccessDeniedException(String message, Throwable cause) {
        super(message, cause, HttpServletResponse.SC_FORBIDDEN);
    }

    public AccessDeniedException(Throwable cause) {
        super(cause, HttpServletResponse.SC_FORBIDDEN);
    }
}
