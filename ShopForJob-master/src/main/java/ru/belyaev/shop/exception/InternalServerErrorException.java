package ru.belyaev.shop.exception;


import javax.servlet.http.HttpServletResponse;

public class InternalServerErrorException extends AbstractErrorException {
	private static final long serialVersionUID = 6735903147426906964L;

	public InternalServerErrorException(String s) {
		super(s, HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
	}

	public InternalServerErrorException(String message, Throwable cause) {
		super(message, cause, HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
	}

	public InternalServerErrorException(Throwable cause) {
		super(cause, HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
	}
}
