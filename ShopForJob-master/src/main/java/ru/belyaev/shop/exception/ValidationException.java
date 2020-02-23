package ru.belyaev.shop.exception;


import ru.belyaev.shop.servlet.AbstractController;

import javax.servlet.http.HttpServletResponse;

public class ValidationException extends AbstractErrorException {
	private static final long serialVersionUID = -6843925636139273536L;

	public ValidationException(String s) {
		super(s,  HttpServletResponse.SC_BAD_REQUEST);
	}

	public ValidationException(String message, Throwable cause) {
		super(message, cause, HttpServletResponse.SC_BAD_REQUEST);
	}

	public ValidationException(Throwable cause) {
		super(cause, HttpServletResponse.SC_BAD_REQUEST);
	}
}
