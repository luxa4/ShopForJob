package ru.belyaev.shop.exception;


public class InternalServerErrorException extends RuntimeException {
	private static final long serialVersionUID = 6735903147426906964L;

	public InternalServerErrorException(String message) {
		super(message);
	}

	public InternalServerErrorException(Throwable cause) {
		super(cause);
	}

	public InternalServerErrorException(String message, Throwable cause) {
		super(message, cause);
	}

}
