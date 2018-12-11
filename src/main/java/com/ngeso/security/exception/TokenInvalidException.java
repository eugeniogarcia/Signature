package com.ngeso.security.exception;

public class TokenInvalidException extends RuntimeException {

	/**
	 *
	 */
	private static final long serialVersionUID = -6749625610557586369L;

	public TokenInvalidException(final String message) {
		super(message);
	}

	public TokenInvalidException(final Throwable cause) {
		super(cause);
	}
}